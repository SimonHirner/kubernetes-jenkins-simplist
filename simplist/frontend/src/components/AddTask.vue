<template>
  <form @submit="addTask" class="col-12 col-sm-10 col-md-8 cl-lg-6">
    <input
      class="form-control"
      type="text"
      v-model="title"
      name="title"
      placeholder="Title"
    />
    <textarea
      type="text"
      v-model="description"
      name="description"
      placeholder="Description"
      class="form-control"
    />
    <button class="btn btn-block btn-outline-primary" type="submit">
      <span class="fa fa-plus"></span>
    </button>
  </form>
</template>

<script>
export default {
  name: "AddTask",
  data() {
    return {
      title: "",
      description: "",
    };
  },
  methods: {
    addTask(e) {
      e.preventDefault();
      const newTaskObj = {
        title: this.title,
        description: this.description,
        completed: false,
      };
      this.$emit("add-task", newTaskObj);
      this.title = "";
      this.description = "";
    },
  },
};
</script>
