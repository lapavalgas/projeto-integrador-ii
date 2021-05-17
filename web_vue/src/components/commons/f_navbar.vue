<template>
  <header class="header">
    <div class="container">
      <a href="" class="grid-4"
        ><router-link to="/"
          ><img
            src="@/img/FS-logo-header.png"
            alt="Fast Service" /></router-link
      ></a>

      <div v-if="isLogged == true" class="grid-12 header_menu">
        <ul style="position: absolute; left: 175px; top: 9px">
          <li>
            <a
              href=""
              style="
          text-decoration:none
            color: inherit;
            color: #000;
            font-weight: 700;
            text-transform: uppercase;
            letter-spacing: 0.1em;
            font-size: 14px;
            line-height: 20px;
            padding: 10px 0;
          "
              ><router-link to="/ofertar">Oferecer serviços</router-link></a
            >
          </li>
        </ul>
      </div>
      <nav class="grid-12 header_menu">
        <ul>
          <li>
            <a href=""><router-link to="/sobre">Sobre</router-link></a>
          </li>
          <li>
            <a href=""><router-link to="/servicos">Servicos</router-link></a>
          </li>
          <li>
            <a href=""><router-link to="/contato">Contato</router-link></a>
          </li>
          <li v-if="isLogged == false">
            <a href=""> <router-link to="/cadastrar">Cadastrar</router-link></a>
          </li>
          <li v-if="isLogged == true">
            <a href="" class="menu_ativo">
              <router-link to="/checkout"
                ><img src="@/img/carrinho.svg" alt="minhaconta" /></router-link
            ></a>
          </li>
          <li v-if="isLogged == true">
            <a href="" class="menu_ativo">
              <router-link to="/cadastrar"
                ><img src="@/img/user.svg" alt="minhaconta" /></router-link
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
  beforeMount: function () {
    if (this.getCookie("fastserviceId") !== undefined) {
      this.isLogged = !this.isLogged;
    }
  },
  data() {
    return {
      isLogged: false,
    };
  },
  methods: {
    // utils
    getCookie: function (name) {
      const value = `; ${document.cookie}`;
      const parts = value.split(`; ${name}=`);
      if (parts.length === 2) return parts.pop().split(";").shift();
    },
  },
};
</script>

<style scoped>
</style>
