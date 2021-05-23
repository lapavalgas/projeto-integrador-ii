<template>
  <section class="contato container fadeInDown" data-anime="1200">
    <div style="width: 100%">
      <h3 class="subtitulo">Informações do serviço</h3>
      <form
        action=""
        class="contato_form grid-8 formphp"
        style="width: 100%; margin-left: 3%"
        v-on:submit.prevent="submit()"
      >
        <div style="width: 60%; margin: auto">
          <div>
            <label for="nservicp">Nome do serviço</label>
            <input
              v-model="form.nome"
              type="text"
              id="nservicp"
              name="nservicp"
              maxlength="22"
              required
            />
            <label for="dservicp">Descrição do serviço</label>
            <input
              v-model="form.descricao"
              type="text"
              id="dservicp"
              name="dservicp"
              maxlength="79"
              required
            />
          </div>
          <div>
            <label for="dservicp">Categoria do serviço</label>

            <select
              style="
                width: 100%;
                margin: 5px;
                border: 4px solid #195e83;
                background: none;
                padding: 7px 10px;
                margin-bottom: 10px;
                margin: 5px 0px 10px;
                outline: none;
                font-size: 14px;
                font-family: Georgia, 'Times New Roman', serif;
              "
              v-model="form.categoria"
              type="text"
              id="dservicp"
              name="dservicp"
              required
            >
              <option value="Informática">Informática</option>
              <option value="Computacao">Computacao</option>
              <option value="Limpeza">Limpeza</option>
              <option value="Faxina">Faxina</option>
              <option value="Mecânica">Mecânica</option>
              <option value="Eletricista">Eletricista</option>
              <option value="Engenharia">Engenharia</option>
            </select>

            <label for="valorservicp">Valor do serviço, R$ :</label>
            <input
              v-model="form.valor"
              type="number"
              id="valorservicp"
              name="valorservicp"
              required
            />
          </div>
          <button id="enviar" name="enviar" type="submit" class="btn btn-preto">
            Cadastrar
          </button>
        </div>
      </form>
    </div>
  </section>
</template>

<script>
export default {
  name: "f_form_servicosOfertar",
  props: {
    msg: String,
  },
  data() {
    return {
      form: {
        usuarioProfissional: {
          usuario_id: this.getCookie("fastserviceId"),
        },
      },
      fastservice_url: "http://localhost:8080",
    };
  },
  methods: {
    submit: async function () {
      try {
        const response = await this.cadastraServicos(
          this.getCookie("fastserviceId"),
          this.form
        );
        if (response.msg === undefined) {
          this.appMsg = "Serviço cadastrado com sucesso!\nID: " + response.servico_id;
        } else {
          this.appMsg = response.msg;
        }
      } catch (error) {
        this.appMsg = "Falha ao cadastrar o serviço!";
      }
      alert(this.appMsg);
      alert("Cadastre quantos serviços quiser!! :-) ")
      window.location.href = "/ofertar";
    },

    cadastraServicos: async function (usuario_id, form_servicoDto) {
      form_servicoDto.statusOperante = true;
      return fetch(
        this.fastservice_url + "/usuarios/" + usuario_id + "/servicos",
        {
          headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
          },
          method: "POST",
          body: JSON.stringify(form_servicoDto),
        }
      ).then((response) => response.json());
    },

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