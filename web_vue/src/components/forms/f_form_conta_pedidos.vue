<template>
  <section>
    <!-- v-if="this.servicosAndamentoMsg === undefined"  -->
    <div id="servicosAndamento" class="qualidade_lista">
      <p class="subtitulo" style="margin-bottom: 20px">Serviços em andamento</p>
      <h1 style="margin: 0 0px 5px 5px">Em andamento</h1>
      <button style="margin-left: 5px">Fechar serviços em andamento</button>
      <div
        style="
          display: flex;
          border: 1px solid #8181816e;
          margin: 5px;
          padding: 10px;
        "
        v-for="servico in servicosSolicitados"
        v-bind:key="servico.pedido_id"
      >
        <div style="min-width: 50%">
          <p>
            Nome: <span>{{ servico.servicoContratado.nome }}</span>
          </p>
          <p>
            Descrição: <span>{{ servico.servicoContratado.descricao }}</span>
          </p>
          <p>
            Categoria: <span>{{ servico.servicoContratado.categoria }}</span>
          </p>
          <p>
            <span>{{ servico.servicoContratado.statusOperante }}</span>
          </p>
          <p>
            <span>{{
              servico.servicoContratado.usuarioProfissional.nome
            }}</span>
          </p>
        </div>
        <div style="margin-left: 2%">
          <div>
            <label for="">Finalizar</label>
            <button>close</button>
          </div>
          <div>
            <label for="">avaliar</label>
            <select>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
            </select>
          </div>
        </div>
        <div style="margin-left: 2%">
          <div>
            <label for="">Escolher data</label>
            <div style="display: flex">
              <input
                type="date"
                id="dataHora"
                name="dataHora"
                maxlength="11"
                required
              />
              <button>add</button>
            </div>
          </div>
          <div>
            <label for="">escolha data</label>
            <select>
              <option value="1">17/05/2021 10:00</option>
              <option value="2">17/05/2021 11:00</option>
              <option value="3">17/05/2021 12:00</option>
              <option value="4">17/05/2021 16:00</option>
              <option value="5">17/05/2021 19:00</option>
            </select>
          </div>
        </div>
      </div>

      <p>teste</p>
      <h1>Finalizados</h1>
    </div>
    <p class="subtitulo" style="margin-bottom: 20px">Serviços solicitados</p>
    <div
      class="qualidade_lista"
      style="margin-left: 62px; margin-bottom: 20px"
    ></div>
  </section>
</template>

<script>
export default {
  name: "f_form_conta_pedidos",
  props: {
    msg: String,
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
    console.log("solicitado " + this.servicosSolicitados);
    console.log(this.servicosSolicitados);
    console.log("solicitado finalizado " + this.servicosSolicitadosFinalizados);
    console.log("solicitado msg " + this.servicosSolicitadosMsg);
    console.log("oferecido " + this.servicosAndamento);
    console.log("oferecido finalizado " + this.servicosAndamentoFinalizados);
    console.log("oferecido msg " + this.servicosAndamentoMsg);
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
