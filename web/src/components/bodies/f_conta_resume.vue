<template>
  <div id="resumeUsuario">
    <p class="subtitulo" style="margin-bottom: 20px">{{ form.nome }}</p>
    <div class="qualidade_lista" style="margin-left: 62px; margin-bottom: 20px">
      <p>{{ form.email }}</p>
      <p>{{ form.telefone }}</p>
      <p>
        {{ form.logradouro }}, {{ form.numero }}, - {{ form.estado }} -
        {{ form.municipio }} - {{ form.bairro }} <br />
        {{ form.complemento }}
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: "f_conta_resume",
  props: {
    msg: String,
    form: {},
  },
};
</script>

<style scoped>
</style>