<template>
  <section>
    <div class="qualidade_lista">
      <p
        v-if="servicosOfertadosIsEmpty() === false"
        class="subtitulo"
        style="margin-bottom: 20px"
      >
        Serviços oferecidos
      </p>
      <div v-if="servicosAndamento.length !== 0">
        <button
          v-on:click="abrirFechar('servicosAndamento')"
          style="min-width: 240px; margin-left: 5px"
        >
          Fechar serviços em andamento
        </button>
      </div>
      <div id="servicosAndamento">
        <f_form_conta_pedidos_card
          v-for="servico in servicosAndamento"
          v-bind:key="servico.pedido_id"
          v-bind:pedido="servico"
        >
        </f_form_conta_pedidos_card>
      </div>
      <div v-if="servicosAndamentoFinalizados.length !== 0">
        <button
          v-on:click="abrirFechar('servicosAndamentoFinalizados')"
          style="min-width: 240px; margin-left: 5px"
        >
          Fechar serviços em andamento
        </button>
      </div>
      <div id="servicosAndamentoFinalizados">
        <f_form_conta_pedidos_card
          v-for="servico in servicosAndamentoFinalizados"
          v-bind:key="servico.pedido_id"
          v-bind:pedido="servico"
        >
        </f_form_conta_pedidos_card>
      </div>
    </div>

    <div class="qualidade_lista">
      <p
        v-if="servicosSolicitadosIsEmpty() === false"
        class="subtitulo"
        style="margin-bottom: 20px"
      >
        serviços solicitados
      </p>
      <div v-if="servicosSolicitados.length !== 0">
        <button
          v-on:click="abrirFechar('servicosSolicitados')"
          style="min-width: 240px; margin-left: 5px"
        >
          Fechar serviços em andamento
        </button>
      </div>
      <div id="servicosSolicitados">
        <f_form_conta_pedidos_card
          v-for="servico in servicosSolicitados"
          v-bind:key="servico.pedido_id"
          v-bind:pedido="servico"
        >
        </f_form_conta_pedidos_card>
      </div>
      <div v-if="servicosSolicitadosFinalizados.length !== 0">
        <button
          v-on:click="abrirFechar('servicosSolicitadosFinalizados')"
          style="min-width: 240px; margin-left: 5px"
        >
          Fechar serviços em andamento
        </button>
      </div>
      <div id="servicosSolicitadosFinalizados">
        <f_form_conta_pedidos_card
          v-for="servico in servicosSolicitadosFinalizados"
          v-bind:key="servico.pedido_id"
          v-bind:pedido="servico"
        >
        </f_form_conta_pedidos_card>
      </div>
    </div>
    <div style="margin-bottom: 2%"></div>
  </section>
</template>

<script>
import f_form_conta_pedidos_card from "@/components/forms/f_form_conta_pedidos_card.vue";
export default {
  name: "f_form_conta_pedidos",
  props: {
    msg: String,
  },
  components: {
    f_form_conta_pedidos_card,
  },
  data() {
    return {
      fastservice_url: "http://localhost:8080",
      servicosAndamento: [],
      servicosAndamentoFinalizados: [],
      servicosAndamentoMsg: "",
      servicosSolicitados: [],
      servicosSolicitadosFinalizados: [],
      servicosSolicitadosMsg: "",
    };
  },
  beforeMount: async function () {
    const responseReadPedidosProfissional = await this.readPedidosProfissional(
      this.getCookie("fastserviceId")
    );
    responseReadPedidosProfissional.forEach((element) => {
      if (responseReadPedidosProfissional[0].msg === undefined) {
        if (element.statusOperante === false) {
          this.servicosAndamentoFinalizados.push(element);
        } else {
          this.servicosAndamento.push(element);
        }
      } else {
        this.servicosAndamentoMsg = responseReadPedidosProfissional[0].msg;
      }
    });
    const responseReadPedidosCliente = await this.readPedidosCliente(
      this.getCookie("fastserviceId")
    );
    responseReadPedidosCliente.forEach((element) => {
      if (responseReadPedidosCliente[0].msg === undefined) {
        if (element.statusOperante === false) {
          this.servicosSolicitadosFinalizados.push(element);
        } else {
          this.servicosSolicitados.push(element);
        }
      } else {
        this.servicosSolicitadosMsg = responseReadPedidosCliente[0].msg;
      }
    });
  },
  methods: {
    readPedidos: async function (usuario_id, pedido_id) {
      return fetch(
        this.fastservice_url +
          "/usuarios/" +
          usuario_id +
          "/pedidos/" +
          pedido_id,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "GET",
        }
      ).then((response) => response.json());
    },

    readPedidosCliente: async function (usuario_id) {
      return fetch(
        this.fastservice_url + "/usuarios/" + usuario_id + "/pedidos/cliente",
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "GET",
        }
      ).then((response) => response.json());
    },

    readPedidosProfissional: async function (usuario_id) {
      return fetch(
        this.fastservice_url +
          "/usuarios/" +
          usuario_id +
          "/pedidos/profissional",
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "GET",
        }
      ).then((response) => response.json());
    },

    // AbrirFechar
    abrirFechar: function (id) {
      // botão 1 : document.getElementById("servicosAndamento").parentNode.children[1].children[1]
      if (document.getElementById(id).style.display === "none") {
        document.getElementById(id).style.display = "";
        if (id.includes("Finalizados")) {
          document.getElementById(
            "servicosSolicitadosFinalizados"
          ).parentNode.children[3].children[0].textContent =
            "Fechar serviços em andamento";
        } else {
          document.getElementById(
            id
          ).parentNode.children[1].children[0].textContent =
            "Fechar serviços em andamento";
        }
      } else {
        document.getElementById(id).style.display = "none";
        if (id.includes("Finalizados")) {
          document.getElementById(
            "servicosSolicitadosFinalizados"
          ).parentNode.children[3].children[0].textContent =
            "Fechar serviços em andamento";
        } else {
          document.getElementById(
            id
          ).parentNode.children[1].children[0].textContent =
            "Fechar serviços em andamento";
        }
      }
    },

    servicosOfertadosIsEmpty: function () {
      let sum = 0;
      sum += this.servicosAndamento.length;
      sum += this.servicosAndamentoFinalizados.length;
      return sum === 0 ? true : false;
    },
    servicosSolicitadosIsEmpty: function () {
      let sum = 0;
      sum += this.servicosSolicitados.length;
      sum += this.servicosSolicitadosFinalizados.length;
      return sum === 0 ? true : false;
    },

    // utils
    getCookie: function (name) {
      const value = `; ${document.cookie}`;
      const parts = value.split(`; ${name}=`);
      if (parts.length === 2) return parts.pop().split(";").shift();
    },
    chunk: function (arr, len) {
      var chunks = [],
        i = 0,
        n = arr.length;

      while (i < n) {
        chunks.push(arr.slice(i, (i += len)));
      }
      return chunks;
    },
  },
};
</script>

<style scoped>
</style>
