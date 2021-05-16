<template>
  <div id="app">
    <f_navbar />
    <f_fastservice_atualizar />
    <f_form_atualizar_usuarios />
    <f_conta_pedidos />
    <f_footer />
  </div>
</template>

<script>
import f_navbar from "@/components/commons/f_navbar.vue";
import f_footer from "@/components/commons/f_footer.vue";
import f_form_atualizar_usuarios from "@/components/forms/f_form_atualizar_usuarios.vue";
import f_fastservice_atualizar from "@/components/headers/f_fastservice_atualizar.vue";
import f_conta_pedidos from "@/components/bodies/f_conta_pedidos.vue";

export default {
  name: "Conta",
  components: {
    f_navbar,
    f_footer,
    f_form_atualizar_usuarios,
    f_fastservice_atualizar,
    f_conta_pedidos,
  },
  props: {
    msg: String,
  },
};
</script>

<style src="@/css/style.css">
</style>
