curl -O http://apache.stu.edu.tw/maven/maven-3/3.6.1/binaries/apache-maven-3.6.1-bin.tar.gz
sudo tar xzf apache-maven-3.6.1-bin.tar.gz -C /usr/local

sudo ln -s /usr/local/apache-maven-3.6.1 /usr/local/maven

export M2_HOME=/usr/local/maven
export PATH=${M2_HOME}/bin:${PATH}

mvn package -Dmaven.test.skip=true
