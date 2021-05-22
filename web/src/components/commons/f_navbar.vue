<template>
  <header class="header">
    <div class="container">
      <a style="width: 125px" href="" class="grid-4"
        ><router-link to="/"
          ><img
            src="@/img/FS-logo-header.png"
            alt="Fast Service" /></router-link
      ></a>

      <div
        style="
          width: 170px;
          position: absolute;
          color: white;
          top: 5px;
          right: 0px;
          <!-- left: 150px; -->
          font-size: 0.8em;
          font-style: italic;"
      >
        <div v-if="isLogged == true">
          <button
            v-on:click.prevent="logoutBtn()"
            style="
              position: absolute;
              top: 17px;
              left: 27px;
              width: 110px;
              text-decoration: none;
              color: #195e83;
              font-weight: 700;
              text-transform: uppercase;
              letter-spacing: 0.1em;
              font-size: 14px;
              padding: 5px 0;
            "
            type="submit"
          >
            SAIR
          </button>
        </div>
        <div v-else>
          <form>
            <div>
              <div>
                <div>
                  <input
                    style="width: 170px"
                    v-model="form.cpf"
                    type="text"
                    id="cpf"
                    name="cpf"
                    maxlength="11"
                    placeholder="CPF"
                  />
                </div>
              </div>
              <button
                v-on:click.prevent="signinBtn()"
                style="width: 170px;    text-decoration:none
                color: #195e83;
                font-weight: 700;
                text-transform: uppercase;
                letter-spacing: 0.1em;
                font-size: 14px;
                padding: 5px 0;"
                type="submit"
              >
                ENTRAR
              </button>
            </div>
          </form>
        </div>
      </div>
      <nav style="text-align: left" class="grid-12 header_menu">
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
  data() {
    return {
      fastservice_url: "http://localhost:8080",
      isLogged: false,
      form: {},
    };
  },
  beforeMount: function () {
    if (this.getCookie("fastserviceId") === undefined) {
      this.isLogged = false;
    } else if (this.getCookie("fastserviceId") !== undefined) {
      this.isLogged = true;
    }
  },

  methods: {
    logoutBtn: function () {
      this.isLogged = false;
      this.killCookie("fastserviceId");
      sessionStorage.clear();
      window.location.reload();
    },

    signinBtn: async function () {
      const response = await this.signIn(this.form.cpf);
      if (response.usuario_id >= 1) {
        this.isLogged = true;
        this.setCookie("fastserviceId", response.usuario_id);
        sessionStorage.setItem("fastserviceData", JSON.stringify(response));
        window.location.reload();
      } else {
        alert("ERROR! Usuário não cadastrado");
      }
    },

    signIn: async function (cpf) {
      return fetch(this.fastservice_url + "/usuarios/login/" + cpf, {
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        method: "GET",
      }).then((response) => response.json());
    },

    // utils
    getCookie: function (name) {
      const value = `; ${document.cookie}`;
      const parts = value.split(`; ${name}=`);
      if (parts.length === 2) return parts.pop().split(";").shift();
    },

    setCookie: function (cookieName, cookieValue) {
      document.cookie = cookieName + "=" + cookieValue;
    },

    killCookie: function (cookieName) {
      document.cookie =
        cookieName + "= ; expires = Thu, 01 Jan 1970 00:00:00 GMT";
    },
  },
};
</script>

<style scoped>
</style>