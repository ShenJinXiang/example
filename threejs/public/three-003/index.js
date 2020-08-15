{
    // 创建场景
    const scene = new THREE.Scene();

    // 创建模型
    const geometry = new THREE.BoxGeometry(100, 100, 100);
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
        mesh.rotateY(0.001 * t);
        // 渲染
        renderer.render(scene, camera);
        requestAnimationFrame(animate);
    };
    animate();
}