{
    // 创建场景
    const scene = new THREE.Scene();

    // 创建模型
    const geometry = new THREE.BoxGeometry(100, 100, 100);

    //长方体 参数：长，宽，高
    // var geometry = new THREE.BoxGeometry(100, 100, 100);
    // 球体 参数：半径60  经纬度细分数40,40
    // var geometry = new THREE.SphereGeometry(60, 40, 40);
    // 圆柱  参数：圆柱面顶部、底部直径50,50   高度100  圆周分段数
    // var geometry = new THREE.CylinderGeometry( 50, 50, 100, 25 );
    // 正八面体
    // var geometry = new THREE.OctahedronGeometry(50);
    // 正十二面体
    // var geometry = new THREE.DodecahedronGeometry(50);
    // 正二十面体
    // var geometry = new THREE.IcosahedronGeometry(50);

    // 材质对象
    const meterial = new THREE.MeshLambertMaterial({
        color: 0x0000FF
    });
    const mesh = new THREE.Mesh(geometry, meterial);
    // 添加到场景
    scene.add(mesh);

    // 点光源
    const point = new THREE.PointLight(0xFFFFFF);
    // 点光源位置
    point.position.set(400, 200, 300);
    // 添加到场景
    scene.add(point);

    // 环境光
    const ambient = new THREE.AmbientLight();
    scene.add(ambient);

    // 相机设置
    let width = window.innerWidth,
        height = window.innerHeight,
        k = width / height,
        // 三维场景显示范围控制系数，系数越大，显示的范围越大
        s = 200,
        camera = new THREE.OrthographicCamera(-s * k, s * k, s, -s, 1, 1000);
    // 相机位置
    camera.position.set(200, 300, 200);
    // //设置相机方向(指向的场景对象)
    camera.lookAt(scene.position);

    // 渲染器对象
    const renderer = new THREE.WebGLRenderer();
    renderer.setSize(width, height);
    // 背景色
    renderer.setClearColor(0xb9d3ff, 1);
    document.body.appendChild(renderer.domElement);
    let t0 = new Date().getTime();
    let animate = () => {
        let t1 = new Date().getTime(),
            t = t1 - t0;
        t0 = t1;
        // mesh.rotateX(0.001 * t);
        mesh.rotateX(0.001 * t);
        // 渲染
        renderer.render(scene, camera);
        requestAnimationFrame(animate);
    };
    animate();
}