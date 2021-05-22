<template>
  <section class="contato container fadeInDown" data-anime="1200">
    <div style="width: 100%">
      <form
        style="width: 100%"
        v-on:submit.prevent="submit()"
        id="form_orcamento"
        class="contato_form grid-8 formphp"
      >
        <div style="display: flex; width: 100%">
          <div
            style="display: block; width: 100%; padding: 50px; padding-top: 0px"
          >
            <h3 class="subtitulo">Contatos</h3>
            <label for="nome">Nome</label>
            <input
              v-model="form.nome"
              type="text"
              id="nome"
              name="nome"
              required
            />

            <label for="cpf">CPF</label>
            <input
              v-model="form.cpf"
              v-on:input="cpfMask"
              type="text"
              id="cpf"
              name="cpf"
              maxlength="11"
              required
            />

            <label for="email">E-mail</label>
            <input
              v-model="form.email"
              type="email"
              id="email"
              name="email"
              required
            />

            <label for="telefone">Telefone</label>
            <input
              v-model="form.telefone"
              v-on:input="telefoneMask"
              type="text"
              id="telefone"
              name="telefone"
              maxlength="15"
              required
            />

            <label for="dataNascimento">Data de nascimento</label>
            <input
              v-model="form.dataNascimento"
              type="date"
              id="dataNascimento"
              name="dataNascimento"
              maxlength="11"
              required
            />

            <label for="genero">Genero</label>
            <div>
              <input
                v-model="form.genero"
                style="display: inline; width: auto"
                type="radio"
                id="M"
                name="genero"
                value="H"
              />
              <label style="display: inline; width: auto" for="M">Homem</label>
              <input
                v-model="form.genero"
                style="display: inline; width: auto"
                type="radio"
                id="M"
                name="genero"
                value="M"
              />
              <label style="display: inline; width: auto" for="F">Mulher</label>
            </div>
          </div>
          <div
            style="display: block; width: 100%; padding: 50px; padding-top: 0px"
          >
            <h3 class="subtitulo">Endereço</h3>
            <label for="cep">CEP</label>
            <input
              v-model="form.cep"
              v-on:input="cepEnderecoMask"
              type="text"
              id="cep"
              name="cep"
              maxlength="9"
              required
            />

            <label for="estado">Estado - UF</label>
            <input
              v-model="form.estado"
              v-on:input="ufEnderecoMask"
              type="text"
              id="estado"
              name="estado"
              maxlength="2"
              required
            />

            <label for="municipio">Municipio</label>
            <input
              v-model="form.municipio"
              type="text"
              id="municipio"
              name="municipio"
              required
            />

            <label for="bairro">Bairro</label>
            <input
              v-model="form.bairro"
              type="text"
              id="bairro"
              name="bairro"
              required
            />

            <label for="logradouro">Logradouro</label>
            <input
              v-model="form.logradouro"
              type="text"
              id="logradouro"
              name="logradouro"
              required
            />

            <label for="numero">Numero</label>
            <input
              v-model="form.numero"
              v-on:input="numeroEnderecoMask"
              type="text"
              id="numero"
              name="numero"
              maxlength="6"
              required
            />

            <label for="complemento">Complemento</label>
            <input
              v-model="form.complemento"
              type="text"
              id="complemento"
              name="complemento"
              maxlength="20"
            />

            <button
              id="enviar"
              name="enviar"
              type="submit"
              class="btn btn-preto"
            >
              Cadastrar
            </button>
          </div>
        </div>
      </form>
    </div>
  </section>
</template>

<script>
export default {
  name: "f_form_cadastro_usuario",
  props: {
    msg: String,
  },
  data() {
    return {
      form: {},
      fastservice_url: "http://localhost:8080",
      appMsg: "",
    };
  },
  beforeMount: async function () {
    if (this.getCookie("fastserviceId") !== undefined) {
      const response = await this.signIn(this.getCookie("fastserviceId"));
      sessionStorage.setItem("fastserviceData", JSON.stringify(response));
      window.location.href = "/conta";
    }
  },
  methods: {
    submit: async function () {
      try {
        const response = await this.cadastraUsuarios(this.form);
        if (response.msg === undefined) {
          document.cookie = "fastserviceId=" + response.usuario_id;
          sessionStorage.setItem("fastserviceData", JSON.stringify(response));
          this.appMsg =
            "Usuário cadastro com sucesso!\nID: " + response.usuario_id;
        } else {
          this.appMsg = response.msg;
        }
      } catch (error) {
        this.appMsg = "Falha ao cadastro o usuário!";
      }
      alert(this.appMsg);
      window.location.href = "/";
    },

    // Masks
    cpfMask: function () {
      this.form.cpf = this.form.cpf
        .replace(/[^\d]/g, "")
        .replace(/(\d{3})(\d{3})(\d{3})(\d{2})/g, "$1.$2.$3-$4");
    },
    telefoneMask: function () {
      this.form.telefone = this.form.telefone
        .replace(/[^\d]/g, "")
        .replace(/(\d{0})(\d{2})(\d{0})(\d{5})/g, "$1($2)$3 $4-");
    },
    cepEnderecoMask: function () {
      this.form.cep = this.form.cep
        .replace(/[^\d]/g, "")
        .replace(/(\d{5})/g, "$1-");
    },
    numeroEnderecoMask: function () {
      this.form.numero = this.form.numero.replace(/[^\d]/g, "");
    },
    ufEnderecoMask: function () {
      this.form.estado = this.form.estado.replace(/^\d+$/, "");
    },

    cadastraUsuarios: async function (form_usuarioDto) {
      return fetch(this.fastservice_url + "/usuarios", {
        headers: {
          Accept: "application/json",
          "Content-Type": "application/json",
        },
        method: "POST",
        body: JSON.stringify(form_usuarioDto),
      }).then((response) => response.json());
    },

    signIn: async function (usuario_id) {
      return fetch(
        this.fastservice_url + "/usuarios/login/" + usuario_id,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "GET",
        }
      ).then((response) => response.json());
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