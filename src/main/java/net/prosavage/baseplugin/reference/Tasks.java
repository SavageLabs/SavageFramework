package net.prosavage.baseplugin.reference;

import net.prosavage.baseplugin.BasePlugin;
import org.bukkit.Bukkit;

public class Tasks {

    public static void run(Callable callable) {
        Bukkit.getScheduler().runTask(BasePlugin.getInstance(), callable::call);
    }

    public static void runAsync(Callable callable) {
        Bukkit.getScheduler().runTaskAsynchronously(BasePlugin.getInstance(), callable::call);
    }

    public static void runLater(Callable callable, long delay) {
        Bukkit.getScheduler().runTaskLater(BasePlugin.getInstance(), callable::call, delay);
    }

    public static void runAsyncLater(Callable callable, long delay) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(BasePlugin.getInstance(), callable::call, delay);
    }

    public static void runTimer(Callable callable, long delay, long interval) {
        Bukkit.getScheduler().runTaskTimer(BasePlugin.getInstance(), callable::call, delay, interval);
    }

    public static void runAsyncTimer(Callable callable, long delay, long interval) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(BasePlugin.getInstance(), callable::call, delay, interval);
    }

    public interface Callable {
        void call();
    }
}
