<template>
  <div
    style="
      display: flex;
      border: 1px solid #8181816e;
      margin: 5px;
      padding: 10px;
    "
  >
    <div style="min-width: 50%">
      <p class="pedidos">
        Nome:
        <span class="pedidosValue">{{ pedido.servicoContratado.nome }}</span>
      </p>
      <p class="pedidos">
        Descrição:
        <span class="pedidosValue">{{
          pedido.servicoContratado.descricao
        }}</span>
      </p>
      <p class="pedidos">
        Categoria:
        <span class="pedidosValue">{{
          pedido.servicoContratado.categoria
        }}</span>
      </p>
      <p class="pedidos profissional">
        Profissional:
        <span>{{ pedido.servicoContratado.usuarioProfissional.nome }}</span>
      </p>
      <p
        class="pedidos"
        v-for="data in pedido.disponibilidade"
        v-bind:key="data.id"
      >
        <span v-if="data.dataSelecionadaPeloUsuario === true"
          >Data: <span class="pedidosValue">{{ data.dataHora }}</span>
        </span>
      </p>
    </div>
    <div style="margin-left: 2%"></div>
    <div style="margin-left: 2%">
      <div v-if="setp1 && !setp2 && !setp3 && !setp4 && !setp5">
        <div style="margin-left: 10px">
          <div style="display: flex">
            <div>
              <span v-if="usuarioType === 'profissional'">
                <h2 for="">Escolher data {{ pedido.pedido_id }}</h2>
                <input
                  v-model="formData"
                  type="date"
                  id="dataHora"
                  name="dataHora"
                  required
                />
                <span>
                  <button
                    v-on:click="addDatahora()"
                    style="margin-left: 5px; width: 127px"
                  >
                    Adicionar
                  </button>

                  <button v-on:click="setUpData()" style="margin-left: 10px">
                    Enviar
                  </button>
                </span>
              </span>
              <span v-else-if="usuarioType === 'cliente'"
                >Aguardando o profissional disponibilizar horarios.</span
              >
              <div style="padding: 5px 0px 0px 0px">
                <p
                  v-for="data in form.disponibilidade"
                  v-bind:key="form.disponibilidade.indexOf(data)"
                >
                  <span style="margin: 2px; 0px;">
                    Disponibilidade: <span>{{ data.dataHora }}</span>
                  </span>
                  <span style="margin: 4px; padding: 4px"
                    ><button v-on:click="removeDatahora(data)">X</button></span
                  >
                </p>
              </div>
            </div>
            <p></p>
            <p></p>
          </div>
        </div>
      </div>
      <div v-if="setp1 && setp2 && !setp3 && !setp4 && !setp5">
        <h2 v-if="usuarioType === 'cliente'" for="">
          Escolher data para o Serviço
        </h2>
        <h2 v-if="usuarioType === 'profissional'" for="">
          Aguardando escolha da data pelo Cliente
        </h2>
        <div style="padding: 5px 0px 0px 0px">
          <p
            v-for="disponibilidade in pedido.disponibilidade"
            v-bind:key="pedido.disponibilidade.indexOf(disponibilidade)"
          >
            <span style="margin: 4px 0px 4px 0px; padding: 4px"
              ><button
                v-if="usuarioType === 'cliente'"
                v-on:click="chooseData(disponibilidade.id)"
              >
                >
              </button></span
            >
            <span style="margin: 2px 2px 2px 0px; 0px;">
              Disponibilidade: <span>{{ disponibilidade.dataHora }}</span>
            </span>
          </p>
        </div>
      </div>
      <div v-if="setp1 && setp2 && setp3 && !setp4 && !setp5">
        <span
          v-if="
            usuarioType === 'cliente' &&
            pedido.servicoFinalizadoCliente === 'false'
          "
        >
          <h5>Não deixe de nos informar se o serviço já foi finalizado!</h5>
          <span
            ><button v-on:click="finalizarServico(usuarioType)">
              Finalizar servico
            </button></span
          ></span
        >
        <span
          v-else-if="
            usuarioType === 'cliente' &&
            pedido.servicoFinalizadoCliente === 'true' &&
            pedido.avaliacaoDoCliente === undefined
          "
        >
          <h5>Não deixe de avaliar o serviço contratado!</h5>
          <form action="">
            <div style="margin-top: 15px; margin: 5px; paddin: 5px">
              <select v-model="form" name="" id="">
                <option value="1">{{ star1 }}</option>
                <option value="2">{{ star2 }}</option>
                <option value="3">{{ star3 }}</option>
                <option value="4">{{ star4 }}</option>
                <option value="5">{{ star5 }}</option>
              </select>
            </div>
            <button
              id="enviar"
              style="margin-top: 10px"
              v-on:click.prevent="avaliar()"
              name="enviar"
              type="submit"
            >
              Avaliar
            </button>
          </form>
        </span>
        <span
          v-else-if="
            usuarioType === 'cliente' &&
            pedido.servicoFinalizadoCliente === 'true' &&
            pedido.servicoFinalizadoProfissional === 'false'
          "
        >
          <h1>Avaliação do serviço</h1>
          <span>{{ getStarImg() }}</span>
        </span>
        <span
          v-if="
            usuarioType === 'profissional' &&
            pedido.servicoFinalizadoProfissional === 'false'
          "
        >
          <h5>Não deixe de nos informar se o serviço já foi finalizado!</h5>
          <span
            ><button v-on:click="finalizarServico(usuarioType)">
              Finalizar servico
            </button></span
          ></span
        >
        <span
          v-else-if="
            usuarioType === 'profissional' &&
            pedido.servicoFinalizadoProfissional === 'true'
          "
        >
          <h5>Aguardando avaliação do cliente!</h5>
        </span>
      </div>
      <div v-if="setp1 && setp2 && setp3 && setp4 && !setp5">
        <h1>Avaliação do serviço</h1>
        <span>{{ getStarImg() }}</span>
      </div>
      <div v-if="setp1 && setp2 && setp3 && setp4 && setp5">
        <h1>Avaliação do serviço</h1>
        <span>{{ getStarImg() }}</span>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "f_form_conta_pedidos_card",
  props: {
    msg: String,
    pedido: {},
  },
  data() {
    return {
      fastservice_url: "http://localhost:8080",
      usuarioType: "",
      setp0: false,
      setp1: false, // profissional disponibiliza datahora  |  cliente ~> cancela
      setp2: false, // cliente escolhe data hora            |  profiss ~> cancela
      setp3: false, // cliente finaliza | profiss finaliza  |   não pode cancelar
      setp4: false, // cliente avalia
      setp5: false, // statusoperante
      formData: {
        dataHora: "",
      },
      form: {
        disponibilidade: [],
      },
      star1: "★☆☆☆☆",
      star2: "★★☆☆☆",
      star3: "★★★☆☆",
      star4: "★★★★☆",
      star5: "★★★★★",
      getStarImg: function () {
        if (this.pedido.avaliacaoDoCliente == 1) {
          return this.star1;
        } else if (this.pedido.avaliacaoDoCliente == 2) {
          return this.star2;
        } else if (this.pedido.avaliacaoDoCliente == 3) {
          return this.star3;
        } else if (this.pedido.avaliacaoDoCliente == 4) {
          return this.star4;
        } else if (this.pedido.avaliacaoDoCliente == 5) {
          return this.star5;
        }
      },
    };
  },
  beforeMount: function () {
    if (this.pedido.cliente.usuario_id == this.getCookie("fastserviceId")) {
      this.usuarioType = "cliente";
    } else {
      this.usuarioType = "profissional";
    }
    // setp 1 : No 1 o profissional deverá libera 3 dataHora de atendimento | + Cancelar
    if (this.pedido.disponibilidade.length === 0) {
      this.setp1 = true;
    }
    // step 2 : No 2 o cliente optará por 1 dataHora disponibilizada | + Cancelar
    this.pedido.disponibilidade.forEach((element) => {
      if (element.dataSelecionadaPeloUsuario === false) {
        this.setp1 = true;
        this.setp2 = true;
      } else if (element.dataSelecionadaPeloUsuario === true) {
        // step 3 : No 3 o profissional e cliente deverão FINALIZAR | - Cancelar
        this.setp1 = true;
        this.setp2 = true;
        this.setp3 = true;
      }
    });
    // setp 4 : No 4 o cliente deverá avaliar o profissional | - Cancelar
    if (
      this.pedido.servicoFinalizadoCliente === true &&
      this.pedido.servicoFinalizadoProfissional === true
    ) {
      this.setp1 = true;
      this.setp2 = true;
      this.setp3 = true;
      this.setp4 = true;
    }
    // setp 5 : no 5 o pedido foi avaliado, o profisisonal recebe nota e finaliza
    if (
      this.pedido.servicoFinalizadoProfissional === "true" &&
      "12345".includes(this.pedido.avaliacaoDoCliente)
    ) {
      this.setp1 = true;
      this.setp2 = true;
      this.setp3 = true;
      this.setp4 = true;
      this.setp5 = true;
    }
  },
  methods: {
    setUpData: async function () {
      try {
        const response = await this.updateProfissionalPedidosDisponibilidades(
          this.getCookie("fastserviceId"),
          this.pedido.pedido_id,
          this.form
        );
        if (response.msg === undefined) {
          this.appMsg =
            "Foi registrado a disponibilidade para os serviços com sucesso!";
          window.location.href = "/conta";
        } else {
          this.appMsg = "ERROR! " + response.msg;
        }
      } catch (error) {
        this.appMsg = "Falha ao atualizar pedido! \n\n\n" + error;
      }
      alert(this.appMsg);
    },
    addDatahora: function () {
      if (this.formData !== null && this.form.disponibilidade.length < 3) {
        this.form.disponibilidade.push({
          dataHora: this.formData,
        });
        this.formData = null;
      }
    },
    chooseData: async function (disponibilidade_id) {
      try {
        const response = await this.updateClientePedidosDisponibilidades(
          this.getCookie("fastserviceId"),
          this.pedido.pedido_id,
          disponibilidade_id
        );
        if (response.msg === undefined) {
          this.appMsg = "Escolheu uma data para o serviço";
          window.location.href = "/conta";
        } else {
          this.appMsg = "ERROR! " + response.msg;
        }
      } catch (error) {
        this.appMsg = "Falha ao atualizar pedido! \n\n\n" + error;
      }
      alert(this.appMsg);
    },
    updateProfissionalPedidosDisponibilidades: async function (
      usuario_id,
      pedido_id,
      form_disponibilidadeDto
    ) {
      return fetch(
        this.fastservice_url +
          "/usuarios/" +
          usuario_id +
          "/pedidos/" +
          pedido_id +
          "/disponibilidades",
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "PUT",
          body: JSON.stringify(form_disponibilidadeDto),
        }
      ).then((response) => response.json());
    },

    updateClientePedidosDisponibilidades: async function (
      usuario_id,
      pedido_id,
      disponibilidade_id
    ) {
      return fetch(
        this.fastservice_url +
          "/usuarios/" +
          usuario_id +
          "/pedidos/" +
          pedido_id +
          "/disponibilidades/" +
          disponibilidade_id,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "PUT",
        }
      ).then((response) => response.json());
    },

    removeDatahora: function (data) {
      const lastDisponibilidade = this.form.disponibilidade;
      this.form.disponibilidade = [];
      lastDisponibilidade.forEach((element) => {
        if (element !== data) {
          this.form.disponibilidade.push(element);
        }
      });
    },
    finalizarServico: async function () {
      try {
        const response = await this.updatePedidosFinalizar(
          this.getCookie("fastserviceId"),
          this.pedido.pedido_id
        );
        if (response.msg === undefined) {
          this.appMsg = "Serviço finalizado com sucesso!";
          window.location.href = "/conta";
        } else {
          this.appMsg = "ERROR! " + response.msg;
        }
      } catch (error) {
        this.appMsg = "Falha ao finalizar o serviço!";
      }
      alert(this.appMsg);
    },

    updatePedidosFinalizar: async function (usuario_id, pedido_id) {
      return fetch(
        this.fastservice_url +
          "/usuarios/" +
          usuario_id +
          "/pedidos/" +
          pedido_id +
          "/finalizar?stts=true",
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "PUT",
        }
      ).then((response) => response.json());
    },

    avaliar: async function () {
      try {
        const response = await this.updateClientePedidosAvaliar(
          this.getCookie("fastserviceId"),
          this.pedido.pedido_id,
          this.form
        );
        if (response.msg === undefined) {
          this.appMsg = "Serviço avaliado com sucesso!!";
          window.location.href = "/conta";
        } else {
          this.appMsg = "ERROR! " + response.msg;
        }
      } catch (error) {
        this.appMsg = "Falha ao avaliar o serviço contratado!! :-(!";
      }
      alert(this.appMsg);
    },

    updateClientePedidosAvaliar: async function (
      usuario_id,
      pedido_id,
      notaAvaliacao
    ) {
      return fetch(
        this.fastservice_url +
          "/usuarios/" +
          usuario_id +
          "/pedidos/" +
          pedido_id +
          "/avaliar?stts=" +
          notaAvaliacao,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "PUT",
        }
      ).then((response) => response.json());
    },

    // utils
    getCookie: function (name) {
      const value = `; ${document.cookie}`;
      const parts = value.split(`; ${name}=`);
      if (parts.length === 2) return parts.pop().split(";").shift();
    },
  },
};
</script>

<style scoped>
.pedidos {
  font-weight: 800;
}
.pedidosValue {
  font-size: 1.15em;
  font-weight: 200;
  margin-left: 5px;
}
.pedidos .profissional {
  font-size: 1.15em;
}
</style>