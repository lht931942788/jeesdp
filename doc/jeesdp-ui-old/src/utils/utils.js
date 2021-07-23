function listToTree(src, prop) {
    let result = [];
    for (let i = 0; i < src.length; i++) {
        if (src[i][prop.pid] === prop.rootKey) {
            result.push(src[i]);
        }
    }
    loadChildren(result, src, prop);
    return result;
}

function loadChildren(parent, src, prop) {
    for (let i = 0; i < parent.length; i++) {
        prop.rootKey = parent[i][prop.id];
        let children = listToTree(src, prop);
        if (children.length > 0) {
            parent[i][prop.children] = children;
        }
    }
}

export function loadPermissions(permissions, resources, permission) {
    for (let i = 0; i < resources.length; i++) {
        let resource = resources[i];
        if (permission === '') {
            permissions.push(resource.code);
        } else {
            permissions.push(permission + ":" + resource.code);
        }
        if (resource.children !== null) {
            if (permission === '') {
                loadPermissions(permissions, resource.children, resource.code)
            } else {
                loadPermissions(permissions, resource.children, permission + ":" + resource.code)
            }
        }
    }
}

export function toTree(src, setting) {
    let prop = {rootKey: '0', pid: 'pid', id: 'id', children: 'children'};
    for (let key in setting) {
        let value = setting[key];
        if (value) {
            prop[key] = value;
        }
    }
    return listToTree(src, prop);
}