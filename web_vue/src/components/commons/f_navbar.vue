<template>
  <header class="header">
    <div class="container">
      <a href="index.html" class="grid-4">
        <img src="@/img/FS-logo-header.png" alt="Fast Service" />
      </a>
      <nav class="grid-12 header_menu">
        <ul>
          <li>
            <a href="sobre.html"
              ><router-link to="/sobre">Sobre</router-link></a
            >
          </li>
          <li>
            <a href="cadastro.html">
              <router-link to="/cadastrar">Cadastrar</router-link></a
            >
          </li>
          <li>
            <a href="servicos.html"
              ><router-link to="/servicos">Servicos</router-link></a
            >
          </li>
          <li>
            <a href="contato.html"
              ><router-link to="/contato">Contato</router-link></a
            >
          </li>
          <li>
            <a href="carrinho.html"
              ><img src="@/img/carrinho.svg" alt="carrinho" /><router-link
                to="/carrinho"
              ></router-link
            ></a>
          </li>
          <li>
            <a href="minhaconta.html" class="menu_ativo">
              <img src="@/img/user.svg" alt="minhaconta" /><router-link
                to="/usuario"
              ></router-link
            ></a>
          </li>
        </ul>
      </nav>
    </div>
  </header>
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
