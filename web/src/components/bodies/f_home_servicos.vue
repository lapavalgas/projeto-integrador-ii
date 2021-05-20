<template>
  <section class="servicos container fadeInDown" data-anime="1600">
    <h2 class="subtitulo">Serviços</h2>
    <ul class="servicos_lista">
      <router-link to="/servicos" style="text-decoration: none; color: inherit">
        <li class="grid-1-3">
          <div class="servicos_icone">
            <img
              src="@/img/FS-icone-servico-limpeza.svg"
              alt="Serviços Domésticos"
            />
          </div>
          <h3>Domésticos</h3>
          <p>
            Os melhores profissionais para realiazação de serciços em sua
            residencia, encontre todas as categorias, limpeza, elétrica,
            hidráulica e muito mais.
          </p>
        </li>

        <li class="grid-1-3">
          <div class="servicos_icone">
            <img src="@/img/FS-icone-servico-ti.svg" alt="Serviços de TI" />
          </div>
          <h3>TI</h3>
          <p>
            Profissionais altamente qualificados para te ajudar a resolver
            problemas aí na sua casa ou na sua empresa, de manutenção a
            programação de sistemas e sites.
          </p>
        </li>

        <li class="grid-1-3">
          <div class="servicos_icone">
            <img
              src="@/img/FS-icone-servico-mecanico.svg"
              alt="Serviços Automotivos"
            />
          </div>
          <h3>Automotivos</h3>
          <p>
            Precisando concertar seu carro? Lavar? Fazer alguma alteração na
            estética? ou apenas boa revsião?Aqui você encontra os melhores
            profissionais!
          </p>
        </li>
      </router-link>
    </ul>

    <div class="call">
      <p>Clique aqui e procure por nossos serviços.</p>
      <a href="servicos.html"
        ><router-link to="/servicos" class="btn btn-preto"
          >Serviços</router-link
        ></a
      >
    </div>
  </section>
</template>

<script>
export default {
  name: "f_home_servicos",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
</style>