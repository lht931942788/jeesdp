import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 * @author LIHAITAO
 */
public class Main {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        String rootDir = "D:\\Work";
        IOFileFilter directories = FileFilterUtils.and(FileFilterUtils.directoryFileFilter(), HiddenFileFilter.VISIBLE);
        IOFileFilter files = FileFilterUtils.and(FileFilterUtils.fileFileFilter(), FileFilterUtils.suffixFileFilter(".txt"));
        IOFileFilter filter = FileFilterUtils.or(directories, files);
        FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir), filter);
        observer.addListener(new FileAlterationListener() {
            /**
             * 文件创建执行
             */
            public void onFileCreate(File file) {
                System.out.println("[新建]:" + file.getAbsolutePath());
            }

            /**
             * 文件创建修改
             */
            public void onFileChange(File file) {
                System.out.println("[修改]:" + file.getAbsolutePath());
            }

            /**
             * 文件删除
             */
            public void onFileDelete(File file) {
                System.out.println("[删除]:" + file.getAbsolutePath());
            }

            /**
             * 目录创建
             */
            public void onDirectoryCreate(File directory) {
                System.out.println("[新建]:" + directory.getAbsolutePath());
            }

            /**
             * 目录修改
             */
            public void onDirectoryChange(File directory) {
                System.out.println("[修改]:" + directory.getAbsolutePath());
            }

            /**
             * 目录删除
             */
            public void onDirectoryDelete(File directory) {
                System.out.println("[删除]:" + directory.getAbsolutePath());
            }

            public void onStart(FileAlterationObserver observer) {
                // TODO Auto-generated method stub
            }

            public void onStop(FileAlterationObserver observer) {
                // TODO Auto-generated method stub
            }

        });
        FileAlterationMonitor monitor = new FileAlterationMonitor(200, observer);
        monitor.start();

    }
}
