export default {
    treeProp: {
        key: 'id',
        parent: 'pid',
        root: '0',
        chinldren: 'chinldren'
    },
    merge(source, result) {
        result = result || {};
        for (let key in source) {
            if (source.hasOwnProperty(key) && !result[key]) {
                result[key] = source[key];
            }
        }
        return result;
    },
    deepClone(source, result) {
        result = Array.isArray(source) ? result || [] : result || {};
        if (source && typeof source === "object") {
            for (let key in source) {
                if (source.hasOwnProperty(key)) {
                    if (source[key] && typeof source[key] === "object") {
                        result[key] = this.deepClone(source[key]);
                    } else if (!result[key]) {
                        result[key] = source[key];
                    }
                }
            }
        }
        return result;
    },
    listToTree(source, prop) {
        if (prop) {
            prop = this.deepClone(this.treeProp, prop);
        } else {
            prop = this.treeProp;
        }
        let result = [];
        for (let i = 0; i < source.length; i++) {
            let record = source[i];
            if (record[prop.parent] === prop.root) {
                source.splice(i, 1);
                let propClone = this.deepClone(prop);
                propClone.root = record[prop.key];
                let chinldren = this.listToTree(source, propClone);
                if (chinldren.length > 0) {
                    record[prop.chinldren] = chinldren;
                }
                result.push(record);
                i--;
            }
        }
        return result;
    }
}

