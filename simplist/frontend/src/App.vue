<template>
  <div id="app" class="container">
    <div class="row top-buffer d-flex justify-content-center">
      <h1>Simplist</h1>
    </div>
    <div class="row top-buffer d-flex justify-content-center">
      <AddTask v-on:add-task="addTask" />
    </div>
    <div class="row top-buffer d-flex justify-content-center">
      <div class="container">
        <p><br /></p>
        <Tasks
          v-bind:tasks="uncompletedTasks"
          v-on:update-task="updateTask"
          v-on:remove-task="removeTask"
          v-bind:completed="false"
          key="tasks"
        />
        <p><br /></p>
        <Tasks
          v-if="completedTasks.length !== 0"
          v-bind:tasks="completedTasks"
          v-on:update-task="updateTask"
          v-on:remove-task="removeTask"
          v-bind:completed="true"
          key="tasks"
        />
      </div>
    </div>
  </div>
</template>

<script>
import Tasks from "@/components/Tasks";
import AddTask from "@/components/AddTask";

const URL = "http://127.0.0.1:8080";

export default {
  name: "App",
  components: {
    AddTask,
    Tasks,
  },
  data() {
    return {
      tasks: [],
      completedTasks: [],
      uncompletedTasks: [],
    };
  },
  mounted() {
    this.getTasks();
  },
  methods: {
    async removeTask(id) {
      await this.deleteTask(id);
      await this.getTasks();
    },
    async updateTask(newTaskObj) {
      await this.putTask(newTaskObj);
      await this.getTasks();
    },
    async addTask(newTaskObj) {
      await this.postTask(newTaskObj);
      await this.getTasks();
    },
    async getTasks() {
      await fetch(URL + "/api/tasks/")
        .then((response) => response.json())
        .then((tasksJson) => {
          this.tasks = tasksJson;
        });
      await fetch(URL + "/api/tasks/completed")
        .then((response) => response.json())
        .then((tasksJson) => {
          this.completedTasks = tasksJson;
        });
      await fetch(URL + "/api/tasks/uncompleted")
        .then((response) => response.json())
        .then((tasksJson) => {
          this.uncompletedTasks = tasksJson;
        });
    },
    async postTask(newTaskObj) {
      const requestOptions = {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(newTaskObj),
      };
      await fetch(URL + "/api/tasks/", requestOptions);
    },
    async putTask(newTaskObj) {
      const requestOptions = {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(newTaskObj),
      };
      await fetch(URL + "/api/tasks/" + newTaskObj.id, requestOptions);
    },
    async deleteTask(id) {
      const requestOptions = {
        method: "DELETE",
      };
      await fetch(URL + "/api/tasks/" + id, requestOptions);
    },
  },
};
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.top-buffer {
  margin-top: 30px;
}
</style>
