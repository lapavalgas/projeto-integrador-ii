<template>
  <section class="servicos container fadeInDown" data-anime="1600">
    <ul class="servicos_lista">
      <f_servicos_card
        v-for="card in line"
        v-bind:key="card.servico_id"
        v-bind:card="card"
      >
      </f_servicos_card>
    </ul>
  </section>
</template>

<script>
import f_servicos_card from "@/components/bodies/servicos/f_servicos_card.vue";

export default {
  name: "f_servicos_servicos",
  props: {
    msg: String,
    line: {
      type: Array,
      default: () => [],
    },
  },
  components: {
    f_servicos_card,
  },
};
</script>

<style scoped>
</style>
