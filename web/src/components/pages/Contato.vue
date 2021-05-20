<template>
  <div id="app">
    <f_navbar />
    <f_fastservice_contato />
    <f_contato_forms />
    <f_contato_maps />
    <f_footer />
  </div>
</template>

<script>
import f_navbar from "@/components/commons/f_navbar.vue";
import f_footer from "@/components/commons/f_footer.vue";
import f_contato_forms from "@/components/bodies/f_contato_forms.vue";
import f_contato_maps from "@/components/bodies/f_contato_maps.vue";
import f_fastservice_contato from "@/components/headers/f_fastservice_contato.vue";

export default {
  name: "Contato",
  components: {
    f_navbar,
    f_footer,
    f_contato_forms,
    f_contato_maps,
    f_fastservice_contato,
  },
  props: {
    msg: String,
  },
};
</script>

<style src="@/css/style.css">
</style>