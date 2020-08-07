<script>
import {Menu, Icon} from 'ant-design-vue';

export default {
  name: 'DemoSubMenu',
  props: {
    menus: {
      type: Array,
      required: true,
    },
  },
  components: {
    Menu,
  },
  render(createElement, context) {
    return createElement(Menu, {
      props: {
        theme: 'dark',
        mode: 'inline'
      },
      style: {
        width: '250px'
      },
    }, this.menus.map((menu) => {
      return renderSubMenu(createElement, menu);
    }))
  }
};

function renderIcon(createElement, menu) {
  if (menu.icon) {
    return createElement(Icon, {})
  }
  return null;
}

function renderSubMenu(createElement, menu) {
  if (menu.children) {
    return createElement(Menu.SubMenu, {
          props: {
            key: menu.id,
            title: menu.name
          }
        }, menu.children.map((item) => {
          return renderSubMenu(createElement, item)
        })
    )
  } else {
    return createElement(Menu.Item, menu.name)
  }
}

/*
function renderSubMenu(createElement, menu) {
  if (menu.children) {
    return createElement(Menu.SubMenu, {
      props: {
        key: menu.id,
        title: menu.name,
      }
    }, menu.children.map((item) => {
      return renderSubMenu(createElement, item)
    }))
  } else {
    return createElement(Menu.Item, menu.name)
  }
}
*/
</script>