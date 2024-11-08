package rechard.learn.tx;

/*
 * Decompiled with CFR.
 *
 * Could not load the following classes:
 *  com.s1.arch.persistence.connection.ConnectionException
 *  com.s1.arch.persistence.connection.ConnectionManager
 *  org.springframework.jdbc.datasource.ConnectionHolder
 *  org.springframework.transaction.support.TransactionSynchronizationManager
 */

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

public class TransactionManager {
    private ThreadLocal<ConnectionHolder> ConnectionCache = new ThreadLocal();
    private DataSource dataSource;
    private static TransactionManager instance = new TransactionManager();

    private TransactionManager() {
    }

    public static TransactionManager getInstance() {
        /* 24*/         return instance;
    }

    public DataSource getDataSource() {
        /* 28*/         return this.dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        /* 31*/         this.dataSource = dataSource;
    }

    private ConnectionHolder get() {
        Connection connection;
        org.springframework.jdbc.datasource.ConnectionHolder holder;
        /* 37*/         if (this.dataSource != null && (holder = (org.springframework.jdbc.datasource.ConnectionHolder)TransactionSynchronizationManager.getResource((Object)this.dataSource)) != null && (connection = holder.getConnection()) != null) {
            ConnectionHolder ch = new ConnectionHolder(connection, Thread.currentThread());
            /* 45*/             ch.setFromSpring(true);
            /* 46*/             return ch;
        }
        /* 50*/         return this.ConnectionCache.get();
    }

    public Connection getConnection() throws SQLException {
        /* 54*/         Connection connection = null;
        ConnectionHolder holder = this.get();
        /* 58*/         if (holder == null) {
            /* 59*/             return null;
        }
        /* 60*/         if (holder.getOwnerThread() == Thread.currentThread()) {
            /* 61*/             connection = holder.getConn();
        }
        /* 63*/         if (connection != null && !connection.isClosed()) {
            /* 64*/             return connection;
        }
        /* 67*/         return null;
    }

    public void setConnection(Connection conn) {
        /* 71*/         this.ConnectionCache.set(new ConnectionHolder(conn, Thread.currentThread()));
    }

    public boolean hasConnectionInThread() throws SQLException {
        return this.getConnection() != null;
    }

    public boolean hasConnectionInThread(Connection conn) throws SQLException {
        Connection connInThreadContext = this.getConnection();
        /*104*/         if (connInThreadContext == null) {
            /*105*/             return false;
        }
        /*107*/         return connInThreadContext == conn;
    }

    public Connection doBegin() throws Exception {
        /*114*/         Connection conn = null;
        /*115*/         conn = ConnectionManager.getConnection();
        /*116*/         conn.setAutoCommit(false);
        /*117*/         this.setConnection(conn);
        /*118*/         return conn;
    }

    public void commit() throws SQLException {
        /*122*/         this.getConnection().commit();
    }

    public void rollback() throws SQLException {
        /*126*/         this.getConnection().rollback();
    }

    public void close() throws SQLException {
        /*130*/         this.getConnection().close();
        /*131*/         this.ConnectionCache.remove();
    }

    public ConnectionHolder suspend() {
        ConnectionHolder holder = this.get();
        /*136*/         if (holder != null) {
            /*137*/             if (holder.isFromSpring()) {
                /*138*/                 TransactionSynchronizationManager.unbindResource((Object)this.dataSource);
            } else {
                /*140*/                 this.ConnectionCache.remove();
            }
        }
        /*143*/         return holder;
    }

    public void resume(ConnectionHolder holder) {
        /*147*/         if (holder != null) {
            /*148*/             if (holder.isFromSpring()) {
                /*149*/                 TransactionSynchronizationManager.bindResource((Object)this.dataSource, (Object)new org.springframework.jdbc.datasource.ConnectionHolder(holder.getConn()));
            } else {
                /*151*/                 this.ConnectionCache.set(holder);
            }
        }
    }

    public static class ConnectionHolder {
        private Connection conn;
        private Thread ownerThread;
        private boolean isFromSpring;

        public ConnectionHolder(Connection conn, Thread ownerThread) {
            this.conn = conn;
            this.ownerThread = ownerThread;
        }

        public Connection getConn() {
            return this.conn;
        }

        public Thread getOwnerThread() {
            return this.ownerThread;
        }

        public boolean isFromSpring() {
            return this.isFromSpring;
        }

        public void setFromSpring(boolean isFromSpring) {
            this.isFromSpring = isFromSpring;
        }
    }
}