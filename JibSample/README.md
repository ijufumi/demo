### Jib Sample

#### Jib
https://github.com/GoogleContainerTools/jib

#### How to works

- Build docker image
```bash
$ mvn compile jib:dockerBuild
```

- Run docker image
```bash
$ docker run -i -t -d myimage /bin/bash
```

