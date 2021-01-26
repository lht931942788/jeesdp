function clone(source, target) {
    target = target || {};
    for (let i in source) {
        if (source.hasOwnProperty(i)) {
            target[i] = source[i];
        }
    }
    return target;
}
