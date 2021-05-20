<template>
  <section class="introducao">
    <div class="container">
      <h1 data-anime="400" class="fadeInDown">Os Melhores Profissionais</h1>
      <blockquote data-anime="800" class="fadeInDown quote-externo">
        <p>
          "Qualquer serviço, altamente qualificados, com a agilidade que você
          precisa"
        </p>
        <cite>FAST SERVICE</cite>
      </blockquote>
      <a data-anime="1200" href="">
        <router-link to="/cadastrar" class="btn">CADASTRE-SE!</router-link>
      </a>
    </div>
  </section>
</template>

<script>
export default {
  name: "f_fastservice_home",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
</style>