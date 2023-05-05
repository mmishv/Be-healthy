function updateProgress(values) {
    for (let i = 0; i < 4; i++) {
        setProgress(document.getElementsByClassName('progress-bar')[i], values[i]);
    }
}

function setProgress(node, percent) {
    node.ariaValuenow = percent;
    node.style.width = percent + '%';
}