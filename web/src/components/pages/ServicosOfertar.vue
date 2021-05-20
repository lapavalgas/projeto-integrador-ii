<template>
  <div id="app">
    <f_navbar />
    <f_fastservice_servicosOfertar />
    <f_form_servicosOfertar />
    <f_footer />
  </div>
</template>

<script>
import f_navbar from "@/components/commons/f_navbar.vue";
import f_fastservice_servicosOfertar from "@/components/headers/f_fastservice_servicosOfertar.vue";
import f_form_servicosOfertar from "@/components/forms/f_form_servicosOfertar.vue";
import f_footer from "@/components/commons/f_footer.vue";

export default {
  name: "ServicosOfertar",
  components: {
    f_navbar,
    f_footer,
    f_form_servicosOfertar,
    f_fastservice_servicosOfertar,
  },
  props: {
    msg: String,
  },
};
</script>

<style src="@/css/style.css">
</style>