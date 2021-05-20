<template>
  <div id="app">
    <f_navbar />
    <f_fastservice_checkout />
    <f_form_checkout_pedidos />
    <f_footer />
  </div>
</template>

<script>
import f_navbar from "@/components/commons/f_navbar.vue";
import f_fastservice_checkout from "@/components/headers/f_fastservice_checkout.vue";
import f_form_checkout_pedidos from "@/components/forms/f_form_checkout_pedidos.vue";
import f_footer from "@/components/commons/f_footer.vue";

export default {
  name: "Checkout",
  components: {
    f_navbar,
    f_footer,
    f_form_checkout_pedidos,
    f_fastservice_checkout,
  },
  props: {
    msg: String,
  },
};
</script>

<style src="@/css/style.css">
</style>