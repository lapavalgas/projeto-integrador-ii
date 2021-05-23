<template>
  <section class="contato fadeInDown" data-anime="1200">
    <div class="contato container fadeInDown" data-anime="1200">
      <section style="width: 100%" class="contato_form grid-8 formphp">
        <div style="display: flex; width: 100%">
          <div
            style="display: block; width: 100%; padding: 50px; padding-top: 0px"
          >
            <h1 style="font-weight: bold" class="topico">SERVIÇO CONTRATADO</h1>
            <ul>
              <li>
                <br />
              </li>
              <li class="topico">Serviço:</li>
              <li class="servicoData">
                <span>{{ servico.nome }} </span>
              </li>
              <li class="topico">Categoria:</li>
              <li class="servicoData">
                <span>{{ servico.categoria }} </span>
              </li>
              <li class="topico">Profissional:</li>
              <li class="servicoData">
                <span>{{ servico.usuarioProfissional.nome }} </span>
              </li>
              <li class="topico">Descrição: <br /></li>
              <li class="servicoData">
                <span>{{ servico.descricao }} </span>
              </li>
              <li class="topico">Preço: <br /></li>
              <li class="servicoData">
                <span>R$: {{ servico.valor }} </span>
              </li>
              <li>
                <form v-on:submit="cancelarCheckout()">
                  <button
                    id="enviar"
                    name="enviar"
                    type="submit"
                    class="btn btn-preto"
                    style="background-color: #f66969"
                  >
                    cancelar
                  </button>
                </form>
              </li>
            </ul>
          </div>
          <div
            style="display: block; width: 100%; padding: 50px; padding-top: 0px"
          >
            <div>
              <p style="font-weight: bold; margin-left: 10px" class="topico">
                FINALIZAR COMPRA:
              </p>
              <form
                style="margin-top: 20px"
                class="contato_form grid-8 formphp"
                v-on:submit.prevent="submit()"
              >
                <label for="formaDePagamento">FORMA DE PAGAMENTO</label>
                <select
                  v-model="form.formaDePagamento"
                  style="
                    width: 100%;
                    margin: 5px;
                    border: 4px solid #195e83;
                    background: none;
                    padding: 7px 10px;
                    margin-bottom: 10px;
                    margin: 5px 0px 10px;
                    outline: none;
                    font-size: 14px;
                    font-family: Georgia, 'Times New Roman', serif;
                  "
                  type="text"
                  id="formaDePagamento"
                  name="formaDePagamento"
                  maxlength="9"
                  required
                  v-on:change="abrirFormDadosCartao()"
                >
                  <option value="PIX">PIX</option>
                  <option value="DEBITO">Debito</option>
                  <option value="CREDITO">Credito</option>
                  <option value="BOLETO">Boleto</option>
                  <option value="DINHEIRO">Dinheiro</option>
                </select>
                <div id="cartao" style="display: none">
                  <label for="ncartao">Número do Cartão</label>
                  <input type="text" id="ncartao" name="ncartao" />
                  <label for="vencartao">Vencimento</label>
                  <input type="text" id="vencartao" name="vencartao" />
                  <label for="cvvcartao">CVV</label>
                  <input type="text" id="cvvcartao" name="cvvcartao" />
                </div>
                <div id="pix" style="display: none">
                  <label for="chavepix">Chave PIX</label>
                  <input type="text" id="chavepix" name="chavepix" />
                </div>

                <button
                  id="enviar"
                  name="enviar"
                  type="submit"
                  class="btn btn-preto"
                >
                  Finalizar
                </button>
              </form>
            </div>
          </div>
        </div>
      </section>
    </div>
  </section>
</template>

<script>
export default {
  name: "f_form_checkout_pedidos",
  props: {
    msg: String,
    card: {},
  },
  data() {
    return {
      fastservice_url: "http://localhost:8080",
      servico: {},
      form: {},
      dadosPagamento: {},
    };
  },
  beforeMount: function () {
    if (this.getCookie("fastserviceId") == undefined) {
      this.cancelarCheckout("servicos");
    }
    if (this.getCookie("fastserviceCheckoutId") == undefined) {
      this.cancelarCheckout("servicos");
    }
    this.servico = JSON.parse(this.getCookie("fastserviceCheckoutId"));
    if (
      this.servico.usuarioProfissional.usuario_id ==
      this.getCookie("fastserviceId")
    ) {
      alert("Você não pode adquirir os próprios serviços!\n\n Que pena! :-(");
      this.cancelarCheckout("servicos");
    }
  },
  methods: {
    submit: async function () {
      try {
        const response = await this.cadastraPedidos(
          this.getCookie("fastserviceId"),
          this.servico.servico_id,
          this.form
        );
        if (response.msg === undefined) {
          this.pedido_data = response;
          this.appMsg =
            "Pedido realizado com sucesso! \nID: " + response.pedido_id;
          alert(this.appMsg);
          document.cookie =
            "fastserviceCheckoutId= ; expires = Thu, 01 Jan 1970 00:00:00 GMT";
          window.location.href = "/conta";
        } else {
          this.appMsg = "ERROR! " + response.msg;
        }
      } catch (error) {
        this.appMsg = "Falha ao atualizar os contatos!";
      }
      if (this.appMsg.includes("Usuário desativado!")) {
        window.location.href = "/servicos";
      }
    },
    cadastraPedidos: async function (usuario_id, servico_id, form_pedidoDto) {
      return fetch(
        this.fastservice_url +
          "/usuarios/" +
          usuario_id +
          "/servicos/" +
          servico_id +
          "/pedidos",
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "POST",
          body: JSON.stringify(form_pedidoDto),
        }
      ).then((response) => response.json());
    },
    cancelarCheckout: function (localToGo) {
      document.cookie =
        "fastserviceCheckoutId= ; expires = Thu, 01 Jan 1970 00:00:00 GMT";
      window.location.href = "/" + localToGo;
    },
    abrirFormDadosCartao: function () {
      if (this.form.formaDePagamento === "PIX") {
        document.getElementById("cartao").style.display = "none";
        document.getElementById("pix").style.display = "";
      }
      if (this.form.formaDePagamento === "DEBITO") {
        document.getElementById("pix").style.display = "none";
        document.getElementById("cartao").style.display = "";
      }
      if (this.form.formaDePagamento === "CREDITO") {
        document.getElementById("pix").style.display = "none";
        document.getElementById("cartao").style.display = "";
      }
      if (this.form.formaDePagamento === "BOLETO") {
        document.getElementById("pix").style.display = "none";
        document.getElementById("cartao").style.display = "none";
      }
      if (this.form.formaDePagamento === "DINHEIRO") {
        document.getElementById("pix").style.display = "none";
        document.getElementById("cartao").style.display = "none";
      }
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
.topico {
  font-family: Georgia, "Times New Roman", serif;
  font-size: 1.01em;
}
.servicoData {
  font-family: Georgia, "Times New Roman", serif;
  margin: 5px 0 25px 0;
  font-size: 0.99em;
}
</style>