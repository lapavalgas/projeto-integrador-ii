<template>
  <section class="missao_sobre container fadeInDown" data-anime="1200">
    <div class="grid-10">
      <h2 class="subtitulo-interno">História, Missão e Visão</h2>
      <p>
        Iniciamos nossa trajetória em 2020, de uma ideia vinda do meio
        acadêmico, hoje essa ideia é real, somos a FastService, te ajudamos a
        encontrar todos os serviços que você precisar.
      </p>
    </div>
    <div class="grid-6">
      <h2 class="subtitulo-interno">Valores</h2>
      <ul>
        <li>- Qualidade Profissional</li>
        <li>- Respeito ao Meio Ambiente</li>
        <li>- Transparência com o Cliente</li>
      </ul>
    </div>

    <div class="grid-16 foto-equipe">
      <img src="@/img/FS-foto-equipe.svg" alt="Equipe FastService" />
    </div>
  </section>
</template>

<script>
export default {
  name: "f_sobre_historia",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
</style>