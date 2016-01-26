package jp.ijufumi.sample.jgit;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class Main {

    public static void main (String...args) throws IOException {
        FileRepositoryBuilder builder = new FileRepositoryBuilder();
        Repository repository = builder.setGitDir(new File("/Users/iju/Documents/IntelliJ/iju_work/demo/.git"))
                .readEnvironment()
                .findGitDir()
                .build();
        Ref ref = repository.findRef("refs/heads/master");

        ObjectId head = repository.resolve("HEAD");

        System.out.println(ref);
        System.out.println(head);

        RevWalk walk = new RevWalk(repository);
        RevCommit commit = walk.parseCommit(head);

        System.out.println(commit);
        System.out.println(commit.getFullMessage());
    }
}
