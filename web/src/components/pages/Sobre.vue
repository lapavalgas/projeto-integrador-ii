<template>
  <div id="app">
    <f_navbar />
    <f_fastservice_home />
    <f_sobre_historia />
    <f_home_sobre />
    <f_footer />
  </div>
</template>

<script>
import f_navbar from "@/components/commons/f_navbar.vue";
import f_footer from "@/components/commons/f_footer.vue";
import f_home_sobre from "@/components/bodies/f_home_sobre.vue";
import f_sobre_historia from "@/components/bodies/f_sobre_historia.vue";
import f_fastservice_home from "@/components/headers/f_fastservice_home.vue";

export default {
  name: "Sobre",
  components: {
    f_navbar,
    f_footer,
    f_sobre_historia,
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