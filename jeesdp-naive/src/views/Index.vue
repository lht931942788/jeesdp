<template>
  <n-config-provider :theme="theme">
    <n-notification-provider>
      <router-view/>
    </n-notification-provider>
  </n-config-provider>
</template>

<script>
import {ref} from 'vue'
import {darkTheme} from 'naive-ui'

export default {
  setup() {
    return {
      darkTheme,
      theme: ref(null)
    }
  }
}
</script>
