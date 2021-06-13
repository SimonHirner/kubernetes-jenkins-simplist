<template>
  <div>
    <h2 v-if="!completed">ToDos</h2>
    <h2 v-if="completed">Done</h2>
  </div>
  <ul class="list-group">
    <li
      class="d-flex justify-content-center list-group-item"
      v-bind:key="task.id"
      v-for="task in tasks"
    >
      <Task
        v-bind:task_prop="task"
        v-on:update-task="updateTask"
        v-on:remove-task="removeTask"
        v-bind:completed="completed"
        key="task"
      />
    </li>
  </ul>
</template>

<script>
import Task from "./Task";

export default {
  name: "Tasks",
  components: {
    Task,
  },
  props: {
    tasks: [],
    completed: Boolean,
  },
  methods: {
    updateTask(newTaskObj) {
      this.$emit("update-task", newTaskObj);
      this.title = "";
    },
    removeTask(id) {
      this.$emit("remove-task", id);
      this.title = "";
    },
  },
};
</script>
