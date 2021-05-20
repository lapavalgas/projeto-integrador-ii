<template>
  <div>
    <section class="quebra">
      <blockquote class="quote-externo container">
        <p>“O serviço que você precisa, na hora que você quiser.”</p>
        <cite>FAST SERVICE</cite>
      </blockquote>
    </section>
    <footer>
      <div class="footer">
        <div class="container">
          <div class="grid-8 footer_historia">
            <h3>Nossa História</h3>
            <p>
              Iniciamos nossa trajetória em 2020, de uma ideia vinda do meio
              acadêmico, hoje essa ideia é real, somos a FastService, te
              ajudamos a encontrar todos os serviços que você precisar.
            </p>
          </div>

          <div class="grid-4 footer_contato">
            <h3>Contato</h3>
            <ul>
              <li>- 48 9 9999-9999</li>
              <li>- contato@fastservice.com.br</li>
              <li>- São José - SC</li>
            </ul>
          </div>

          <div class="grid-4 footer_redes">
            <h3>Nossas Redes</h3>
            <ul>
              <li>
                <a href="http://facebook.com/fast" target="_blank"
                  ><img
                    src="@/img/redes-sociais/facebook.svg"
                    alt="Facebook Fast Service"
                /></a>
              </li>
              <li>
                <a href="http://instagram.com" target="_blank"
                  ><img
                    src="@/img/redes-sociais/instagram.svg"
                    alt="Instagram Fast Service"
                /></a>
              </li>
              <li>
                <a href="http://twitter.com" target="_blank"
                  ><img
                    src="@/img/redes-sociais/twitter.svg"
                    alt="Twitter Fast Service"
                /></a>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div class="copy">
        <div class="container">
          <p class="grid-16">Fast Service 2021 - Direitos Reservados.</p>
        </div>
      </div>
    </footer>
  </div>
</template>

<script>
export default {
  name: "f_navbar",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
</style>