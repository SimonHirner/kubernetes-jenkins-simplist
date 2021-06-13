<template>
  <div v-bind:class="{ completed: task.completed }">
    <h3>{{ task_prop.title }}</h3>
    <p>{{ task_prop.description }}</p>
    <button
      class="btn btn-outline-primary border-0 ml-2"
      @click="updateTask"
      v-if="!completed"
    >
      <span class="fa fa-check"></span>
    </button>
    <button
      class="btn btn-outline-primary border-0 ml-2"
      @click="updateTask"
      v-if="completed"
    >
      <span class="fa fa-undo"></span>
    </button>
    <button @click="removeTask" class="btn btn-outline-danger border-0">
      <span class="fa fa-trash"></span>
    </button>
  </div>
</template>

<script>
export default {
  name: "Task",
  props: {
    task_prop: {},
    completed: Boolean,
  },
  data() {
    return {
      task: this.task_prop,
    };
  },
  methods: {
    updateTask(e) {
      e.preventDefault();
      this.task.completed = !this.task.completed;
      const newTaskObj = {
        id: this.task.id,
        title: this.task.title,
        description: this.task.description,
        completed: this.task.completed,
      };
      this.$emit("update-task", newTaskObj);
      this.title = "";
    },
    removeTask(e) {
      e.preventDefault();
      this.$emit("remove-task", this.task.id);
      this.title = "";
    },
  },
};
</script>

<style scoped>
.completed {
  text-decoration: line-through;
}
</style>
