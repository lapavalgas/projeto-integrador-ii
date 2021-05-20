<template>
  <div id="app">
    <f_navbar />
    <f_fastservice_cadastro />
    <f_form_cadastro_usuario />
    <f_footer />
  </div>
</template>

<script>
import f_navbar from "@/components/commons/f_navbar.vue";
import f_fastservice_cadastro from "@/components/headers/f_fastservice_cadastro.vue";
import f_form_cadastro_usuario from "@/components/forms/f_form_cadastro_usuario.vue";
import f_footer from "@/components/commons/f_footer.vue";

export default {
  name: "Cadastrar",
  components: {
    f_navbar,
    f_footer,
    f_form_cadastro_usuario,
    f_fastservice_cadastro,
  },
  props: {
    msg: String,
  },
};
</script>

<style src="@/css/style.css">
</style>