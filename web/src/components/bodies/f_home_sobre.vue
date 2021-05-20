<template>
  <section class="qualidade container">
    <h2 class="subtitulo">Qualidade</h2>
    <img src="@/img/FS-logo_fs_azul.png" alt=" Fast Service" />
    <ul class="qualidade_lista">
      <li class="grid-1-3">
        <h3>Agilidade</h3>
        <p>Rápida resolução do seu problema.</p>
      </li>
      <li class="grid-1-3">
        <h3>Confiabilidade</h3>
        <p>Profissionais verificados e recomendados.</p>
      </li>
      <li class="grid-1-3">
        <h3>Preço Justo</h3>
        <p>Preço que cabe no seu orçamento.</p>
      </li>
    </ul>
    <div class="call">
      <p>Conheça mais a nossa história.</p>

      <a href="servicos.html"
        ><router-link to="/sobre" class="btn btn-preto">Sobre</router-link></a
      >
    </div>
  </section>
</template>

<script>
export default {
  name: "f_home_sobre",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
</style>