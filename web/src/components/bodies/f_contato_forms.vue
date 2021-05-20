<template>
  <section class="contato container fadeInDown" data-anime="1200">
    <form
      id="form_orcamento"
      v-on:submit.prevent="submit()"
      class="contato_form grid-8 formphp"
    >
      <label for="nome">Nome</label>
      <input type="text" id="nome" name="nome" required />
      <label for="email">E-mail</label>
      <input type="email" id="email" name="email" required />
      <label for="telefone">Telefone</label>
      <input type="text" id="telefone" name="telefone" />

      <label class="nao-aparece">Se você não é um robô, deixe em branco.</label>
      <input type="text" class="nao-aparece" name="leaveblank" />
      <label class="nao-aparece"
        >Se você não é um robô, não mude este campo.</label
      >
      <input
        type="text"
        class="nao-aparece"
        name="dontchange"
        value="http://"
      />

      <label for="mensagem">Mensagem</label>
      <textarea id="mensagem" name="mensagem" required></textarea>
      <button id="enviar" name="enviar" type="submit" class="btn btn-preto">
        Enviar
      </button>
    </form>

    <div class="contato_dados grid-8">
      <h3>Dados</h3>
      <span>+55 48 9 9999-9999</span>
      <span>orcamento@fastservice.com.br</span>
      <span>AV. Presidente Kennedy - 1000</span>
      <span>São José - SC - Brasil</span>
      <h3>Redes Sociais</h3>
      <ul>
        <li>
          <a href="http://facebook.com" target="_blank"
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
  </section>
</template>

<script>
export default {
  name: "f_contato_forms",
  props: {
    msg: String,
  },
  methods: {
    submit: function () {
      alert("Seu e-mail foi encaminhado. \nAgradecemos.");
      window.location.href = "/";
    },
  },
};
</script>

<style scoped>
</style>