
<template>
  <li class="grid-1-3">
    <div class="servicos_icone">
      <img :src="img" alt="Serviços Domésticos" />
    </div>
    <h3>{{ card.nome }}</h3>
    <h5 style="margin-top: 10px;">{{ card.usuarioProfissional.nome }} | {{ card.categoria }}</h5>
    <p>{{ card.descricao }}</p>
    <div>
      <button
        style="background-color: #195e83; border: solid; margin: 5px"
        id="enviar"
        name="enviar"
        type="submit"
        class="btn btn-preto"
      >
        Contratar
      </button>
    </div>
  </li>
</template>

<script>
export default {
  name: "f_servicos_servicos",
  props: {
    msg: String,
    card: {},
  },
  beforeMount: function () {
    this.setupImg(this.card.categoria);
  },
  methods: {
    setupImg: function (categoria) {
      this.img = this.img_mecanico;
      if (categoria !== undefined) {
        categoria = categoria.toLowerCase();
        if (
          categoria == "it" ||
          categoria == "ti" ||
          categoria == "informatica" ||
          categoria == "informática" ||
          categoria == "computacao"
        ) {
          this.img = this.img_ti;
        }

        if (
          categoria == "casa" ||
          categoria == "limpeza" ||
          categoria == "arrumacao" ||
          categoria == "faxina"
        ) {
          this.img = this.img_limpeza;
        }

        if (
          categoria == "mecanico" ||
          categoria == "concerto" ||
          categoria == "eletricista" ||
          categoria == "engenharia"
        ) {
          this.img = this.img_mecanico;
        }
      }
    },
  },
  data() {
    return {
      img_limpeza: require("@/img/FS-icone-servico-limpeza.svg"),
      img_ti: require("@/img/FS-icone-servico-ti.svg"),
      img_mecanico: require("@/img/FS-icone-servico-mecanico.svg"),
      img: "",
    };
  },
};
</script>

<style scoped>
</style>
