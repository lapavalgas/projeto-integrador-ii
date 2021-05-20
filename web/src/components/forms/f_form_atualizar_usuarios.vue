<template>
  <section class="contato container fadeInDown" data-anime="1200">
    <div>
      <f_conta_resume v-bind:key="form.usuario_id" v-bind:form="form" />

      <f_form_conta_pedidos />

      <div>
        <button
          style="margin-left: 62px"
          v-on:click.prevent="abrirFecharFormEditar()"
          id="enviar"
          name="enviar"
          type="submit"
          class="btn btn-preto"
        >
          Atualizar cadastro
        </button>
        <button
          v-on:click.prevent="deletarConta()"
          style="
            position: absolute;
            right: 98px;
            background-color: #f66969;
            width: 253px;
          "
          id="enviar"
          name="enviar"
          type="submit"
          class="btn btn-preto"
        >
          Deletar conta
        </button>
      </div>
    </div>
    <div id="updateUsuario" style="display: none">
      <form
        style="width: 100%; margin-top: 25px"
        id="form_orcamento"
        class="contato_form grid-8 formphp"
      >
        <div style="display: flex; width: 100%">
          <div
            style="display: block; width: 100%; padding: 50px; padding-top: 0px"
          >
            <h3 class="subtitulo">Contatos</h3>
            <label style="display: none" for="nome">Nome</label>
            <input
              style="background-color: #195e8352; display: none"
              v-model="form.nome"
              type="text"
              id="nome"
              name="nome"
              required
              disabled
            />

            <label style="display: none" for="cpf">CPF</label>
            <input
              style="background-color: #195e8352; display: none"
              v-model="form.cpf"
              v-on:input="cpfMask"
              type="text"
              id="cpf"
              name="cpf"
              maxlength="11"
              required
              disabled
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

            <label style="display: none" for="dataNascimento"
              >Data de nascimento</label
            >
            <input
              style="display: none"
              v-model="form.dataNascimento"
              type="date"
              id="dataNascimento"
              name="dataNascimento"
              maxlength="11"
              required
              disable
            />

            <label style="display: none" for="genero">Genero</label>
            <div>
              <input
                v-model="form.genero"
                style="display: none; width: auto"
                type="radio"
                id="M"
                name="genero"
                value="H"
                disabled
              />
              <label style="display: none; width: auto" for="M">Homem</label>
              <input
                v-model="form.genero"
                style="display: none; width: auto"
                type="radio"
                id="M"
                name="genero"
                value="M"
                disabled
              />
              <label style="display: none; width: auto" for="F">Mulher</label>
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

            <div style="display: flex; margin-top: 20px">
              <button
                v-on:click.prevent="submit()"
                id="enviar"
                name="enviar"
                type="submit"
                class="btn btn-preto"
              >
                Atualizar
              </button>
            </div>
          </div>
        </div>
      </form>
    </div>
  </section>
</template>

<script>
import f_conta_resume from "@/components/bodies/f_conta_resume.vue";
import f_form_conta_pedidos from "@/components/forms/f_form_conta_pedidos.vue";
export default {
  name: "f_form_atualizar_usuarios",
  props: {
    msg: String,
  },
  components: {
    f_conta_resume,
    f_form_conta_pedidos,
  },
  data() {
    return {
      editable: false,
      form: {},
      fastservice_url: "http://localhost:8080",
      appMsg: "",
    };
  },
  beforeMount: async function () {
    if (this.getCookie("fastserviceId") === undefined) {
      window.location.href = "/cadastrar";
    }
    this.form = await this.readUsuarios(this.getCookie("fastserviceId"));
    if (this.form.msg !== undefined) {
      document.cookie =
        "fastserviceId= ; expires = Thu, 01 Jan 1970 00:00:00 GMT";
      window.location.href = "/cadastrar";
    }
  },
  methods: {
    // UI
    submit: async function () {
      try {
        const responseContatos = await this.updateUsuariosContatos(
          this.getCookie("fastserviceId"),
          this.form
        );
        if (responseContatos.msg === undefined) {
          alert("Contatos atualizados!");
        } else {
          this.appMsg = responseContatos.msg;
          alert(this.appMsg);
        }
        const responseEnderecos = await this.updateUsuariosEnderecos(
          this.getCookie("fastserviceId"),
          this.form
        );
        if (responseEnderecos.msg === undefined) {
          alert("Enderecos atualizados!");
        } else {
          this.appMsg = responseEnderecos.msg;
          alert(this.appMsg);
        }
      } catch (error) {
        this.appMsg = "Falha ao cadastro o usuário!";
      }
      this.abrirFecharFormEditar();
    },
    deletarConta: async function () {
      try {
        const response = await this.deleteUsuarios(
          this.getCookie("fastserviceId")
        );
        document.cookie =
          "fastserviceId= ; expires = Thu, 01 Jan 1970 00:00:00 GMT";
        sessionStorage.removeItem("fastserviceData");
        this.appMsg = response.msg;
      } catch (error) {
        this.appMsg = "Falha ao deletar o usuário!";
      }
      alert("conta deletada. " + this.appMsg);
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
    readUsuarios: async function (usuario_id) {
      let response = await fetch(
        this.fastservice_url + "/usuarios/" + usuario_id,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "GET",
        }
      );
      let responseData = await response.json();
      return responseData;
    },

    deleteUsuarios: async function (usuario_id) {
      let response = await fetch(
        this.fastservice_url + "/usuarios/" + usuario_id,
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "DELETE",
        }
      );
      let responseData = await response.json();
      return responseData;
    },

    updateUsuariosContatos: async function (usuario_id, form_contatoDto) {
      let response = await fetch(
        this.fastservice_url + "/usuarios/" + usuario_id + "/contatos",
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "PUT",
          body: JSON.stringify(form_contatoDto),
        }
      );
      let responseData = await response.json();
      return responseData;
    },

    updateUsuariosEnderecos: async function (usuario_id, form_enderecoDto) {
      let response = await fetch(
        this.fastservice_url + "/usuarios/" + usuario_id + "/enderecos",
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "PUT",
          body: JSON.stringify(form_enderecoDto),
        }
      );
      let responseData = await response.json();
      return responseData;
    },

    // utils
    getCookie: function (name) {
      const value = `; ${document.cookie}`;
      const parts = value.split(`; ${name}=`);
      if (parts.length === 2) return parts.pop().split(";").shift();
    },
    abrirFecharFormEditar: function () {
      if (this.editable) {
        document.getElementById("updateUsuario").style.display = "none";
        this.editable = !this.editable;
      } else {
        document.getElementById("updateUsuario").style.display = "contents";
        this.editable = !this.editable;
      }
    },
  },
};
</script>

<style scoped>
</style>