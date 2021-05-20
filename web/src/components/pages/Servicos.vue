<template>
  <div id="app">
    <f_navbar />
    <f_fastservice_servicos />
    <f_servicos_servicos />
    <f_footer />
  </div>
</template>

<script>
import f_navbar from "@/components/commons/f_navbar.vue";
import f_footer from "@/components/commons/f_footer.vue";
import f_fastservice_servicos from "@/components/headers/f_fastservice_servicos.vue";
import f_servicos_servicos from "@/components/bodies/f_servicos_servicos.vue";

export default {
  name: "Market",
  components: {
    f_navbar,
    f_footer,
    f_fastservice_servicos,
    f_servicos_servicos,
  },
  props: {
    msg: String,
  },
};
</script>

<style src="@/css/style.css">
</style>