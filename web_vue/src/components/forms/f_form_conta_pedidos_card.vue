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
      <p>
        Nome: <span>{{ pedido.servicoContratado.nome }}</span> ::: ID ~>
        <span>{{ pedido.pedido_id }}</span>
      </p>
      <p>
        Descrição: <span>{{ pedido.servicoContratado.descricao }}</span>
      </p>
      <p>
        Categoria: <span>{{ pedido.servicoContratado.categoria }}</span>
      </p>
      <p>
        <span>{{ pedido.servicoContratado.usuarioProfissional.nome }}</span>
      </p>
      <button 
        v-if="((!setp3) && !pedido.servicoContratado.statusOperante)"
        style="max-height: 34px; padding: 4px; margin-top: 4px"
        class="btn btn-preto"
      >
        Cancelar
      </button>
    </div>
    <div style="margin-left: 2%">
      <!-- <div>
        <label for="">Finalizar</label>
        <button>close</button>
      </div> -->
      <!-- <div>
        <label for="">avaliar</label>
        <select>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
        </select>
      </div> -->
    </div>
    <div style="margin-left: 2%">
      <!-- <div>
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
      </div> -->
      <!-- <div>
        <label for="">escolha data</label>
        <select>
          <option value="1">17/05/2021 10:00</option>
          <option value="2">17/05/2021 11:00</option>
          <option value="3">17/05/2021 12:00</option>
          <option value="4">17/05/2021 16:00</option>
          <option value="5">17/05/2021 19:00</option>
        </select>
      </div> -->
      <div v-if="setp1 === true">
        <p>setp 1</p>
      </div>
      <div v-if="setp2 === true">
        <p>setp 2</p>
      </div>
      <div v-if="setp3 === true">
        <p>setp 3</p>
      </div>
      <div v-if="setp4 === true">
        <p>setp 4</p>
      </div>
      <div v-if="setp5 === true">
        <p>setp 5</p>
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
      setp0: false,
      setp1: false, // profissional disponibiliza datahora  |  cliente ~> cancela
      setp2: false, // cliente escolhe data hora            |  profiss ~> cancela
      setp3: false, // cliente finaliza | profiss finaliza  |   não pode cancelar
      setp4: false, // cliente avalia
      setp5: false, // statusoperante
    };
  },
  beforeMount: function () {
    // setp 1 : No 1 o profissional deverá libera 3 dataHora de atendimento | + Cancelar
    if (this.pedido.disponibilidade.length === 0) {
      console.log(
        "pedido:" +
          this.pedido.pedido_id +
          "::step1::::" +
          this.pedido.disponibilidade.length
      );
      this.setp1 = true;
    }
    // step 2 : No 2 o cliente optará por 1 dataHora disponibilizada | + Cancelar
    this.pedido.disponibilidade.forEach((element) => {
      if (element.dataSelecionadaPeloUsuario === "false") {
        console.log(
          "pedido:" +
            this.pedido.pedido_id +
            "::step2::::" +
            element.dataSelecionadaPeloUsuario
        );
        this.setp1 = true;
        this.setp2 = true;
      } else if (element.dataSelecionadaPeloUsuario === "true") {
        console.log(
          "pedido:" +
            this.pedido.pedido_id +
            "::step3::::" +
            element.dataSelecionadaPeloUsuario
        );
        // step 3 : No 3 o profissional e cliente deverão FINALIZAR | - Cancelar
        this.setp1 = true;
        this.setp2 = true;
        this.setp3 = true;
      }
    });
    // setp 4 : No 4 o cliente deverá avaliar o profissional | - Cancelar
    if (
      this.pedido.servicoFinalizadoCliente === "true" &&
      this.pedido.servicoFinalizadoProfissional === "true"
    ) {
      console.log(
        "pedido:" +
          this.pedido.pedido_id +
          "::step4::::C." +
          this.pedido.servicoFinalizadoCliente +
          "P." +
          this.pedido.servicoFinalizadoProfissional
      );
      this.setp1 = true;
      this.setp2 = true;
      this.setp3 = true;
      this.setp4 = true;
    }
    // setp 5 : no 5 o pedido foi avaliado, o profisisonal recebe nota e finaliza
    if ("12345".includes(this.pedido.avaliacaoDoCliente)) {
      console.log(
        "pedido:" +
          this.pedido.pedido_id +
          "::step5::::" +
          "12345".includes(this.pedido.avaliacaoDoCliente)
      );
      this.setp1 = true;
      this.setp2 = true;
      this.setp3 = true;
      this.setp4 = true;
      this.setp5 = true;
      console.log(this.step5);
    }
  },
};
</script>

<style scoped>
</style>
