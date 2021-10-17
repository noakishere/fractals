var angle = 0;

var slider;

function setup() {
    var canvas = createCanvas(500, 500);
    canvas.parent("#centerme");
    slider = createSlider(0, TWO_PI, PI / 4, 0.01);
    slider.parent("#centerme");
}

function draw() {
    background(51);
    angle = slider.value();
    stroke(255);
    translate(250, height);
    branch(100);

}

function branch(len) {

    line(0, 0, 0, -len);
    translate(0, -len);
    // rotate(angle);
    if (len > 4)
    {
        push();
        rotate(angle);
        branch( len * 0.67 );
        pop();
        push();
        rotate(-angle);
        branch(len * 0.67);
        pop();
    }    
}