<template>
  <div id="app">
    <f_navbar />
    <f_fastservice_home />
    <f_home_servicos />
    <f_separador />
    <f_home_sobre />
    <f_footer />
  </div>
</template>

<script>
import f_navbar from "@/components/commons/f_navbar.vue";
import f_footer from "@/components/commons/f_footer.vue";
import f_separador from "@/components/commons/f_separador.vue";
import f_home_sobre from "@/components/bodies/f_home_sobre.vue";
import f_home_servicos from "@/components/bodies/f_home_servicos.vue";
import f_fastservice_home from "@/components/headers/f_fastservice_home.vue";

export default {
  name: "Home",
  components: {
    f_navbar,
    f_footer,
    f_home_servicos,
    f_separador,
    f_home_sobre,
    f_fastservice_home,
  },
  props: {
    msg: String,
  },
};
</script>

<style src="@/css/style.css">
</style>