function updateProgress(values) {
    for (let i = 0; i < 4; i++) {
        let node = document.getElementsByClassName('progress-bar')[i];
        node.ariaValuenow = values[i];
        node.style.width = values[i] + '%';
    }
}