{
    const option = {
        num: 20,
        width: 10,
        radius: 4,
        color: 0x00ff00
    };

    const drawer = {
        start() {
            // 场景
            drawer.scene = new THREE.Scene();
            drawer.initCamera();
            drawer.initLight();
            drawer.initRender();
            drawer.initElements();
            drawer.render();
            drawer.scene.add(new THREE.AxesHelper(250));
            //创建控件对象
            drawer.controls = new THREE.OrbitControls(drawer.camera, drawer.renderer.domElement);
            drawer.controls.addEventListener('change', drawer.render);//监听鼠标、键盘事件
        },
        initElements() {
            let meterial = new THREE.MeshLambertMaterial({
                color: 0x0000FF,
                wireframe: true
            });
            drawer.scene.add(new THREE.Mesh(new THREE.IcosahedronGeometry(200), meterial));
        },
        initLight() {
            // 点光源
            drawer.point = new THREE.PointLight(0xFFFFFF);
            // 点光源位置
            drawer.point.position.set(400, 200, 300);
            drawer.scene.add(drawer.point);
            // 环境光
            drawer.ambient = new THREE.AmbientLight()
            drawer.scene.add(drawer.ambient);
        },
        initCamera() {
            // 相机设置
            drawer.w = window.innerWidth;
            drawer.h = window.innerHeight;
            drawer.k = drawer.w / drawer.h;
            // 三维场景显示范围控制系数，系数越大，显示的范围越大
            let s = 200;
            drawer.camera = new THREE.OrthographicCamera(-s * drawer.k, s * drawer.k, s, -s, 1, 1000);
            // 相机位置
            drawer.camera.position.set(0, 0, 200);
            // 设置相机方向(指向的场景对象)
            drawer.camera.lookAt(drawer.scene.position);
        },
        initRender() {
            // 渲染器对象
            drawer.renderer = new THREE.WebGLRenderer();
            drawer.renderer.setSize(drawer.w, drawer.h);
            // 背景色
            drawer.renderer.setClearColor(0xb9d3ff, 1);
            document.body.appendChild(drawer.renderer.domElement);
        },
        render() {
            // 渲染
            drawer.renderer.render(drawer.scene, drawer.camera);
        }
    };
    drawer.start();
}