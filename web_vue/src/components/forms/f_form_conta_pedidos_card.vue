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
        Nome: <span>{{ pedido.servicoContratado.nome }}</span>
      </p>
      <p>
        Descrição: <span>{{ pedido.servicoContratado.descricao }}</span>
      </p>
      <p>
        Categoria: <span>{{ pedido.servicoContratado.categoria }}</span>
      </p>
      <p>
        <span>{{ pedido.servicoContratado.statusOperante }}</span>
      </p>
      <p>
        <span>{{ pedido.servicoContratado.usuarioProfissional.nome }}</span>
      </p>
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
      setp1: false, // profissional disponibiliza datahora  |  cliente ~> cancela
      setp2: false, // cliente escolhe data hora            |  profiss ~> cancela
      setp3: false, // cliente finaliza | profiss finaliza  |   não pode cancelar
      setp4: false, // cliente avalia
      setp5: false, // statusoperante
    };
  },
  beforeMount: function () {
    if (this.pedido.disponibilidade.length === 0) {
      this.setp1 = true;
    } else {
      this.pedido.disponibilidade.forEach((element) => {
        if (element.dataSelecionadaPeloUsuario === true) {
          this.setp1 = false;
          this.setp2 = true;
        }
      });
      if (
        this.pedido.servicoFinalizadoCliente === true &&
        this.pedido.servicoFinalizadoProfissional === true
      ) {
        this.setp1 = false;
        this.setp2 = false;
        this.setp3 = true;
      }
    }
    if (this.setp3 === true) {
      this.setp1 = false;
      this.setp2 = false;
      this.setp3 = false;
      this.setp4 = true;
    }
    if (this.pedido.avaliacaoDoCliente >= 1) {
      this.setp1 = false;
      this.setp2 = false;
      this.setp3 = false;
      this.setp4 = false;
      this.setp5 = true;
    }
  },
};
</script>

<style scoped>
</style>
