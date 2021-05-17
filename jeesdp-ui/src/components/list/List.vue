<template>
  <ul class="list">
    <li v-for="item in data" class="list-item">
      <div>
        {{ item.title }}
      </div>
      <div>
        {{ item.content }}
      </div>
    </li>
  </ul>
</template>

<script>
export default {
  name: "List",
  props: {
    data: {
      type: Array,
      required: true
    }
  }
}
</script>

<style scoped>
.list-item {
  margin: 0;
  padding: 0;
  list-style: none;
}
</style>
