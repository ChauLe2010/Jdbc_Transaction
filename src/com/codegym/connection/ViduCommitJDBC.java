package com.codegym.connection;

import java.sql.*;

public class ViduCommitJDBC {
           // Ten cua driver va dia chi URL cua co so du lieu
        static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        static final String DB_URL = "jdbc:mysql://localhost:3306/sinhvien";

        //  Ten nguoi dung va mat khau cua co so du lieu
        static final String USER = "test";
        static final String PASS = "test";

        public static void main(String[] args) {
            Connection conn = null;
            Statement stmt = null;
            try{
                // Buoc 2: Dang ky Driver
                Class.forName("com.mysql.jdbc.Driver");

                // Buoc 3: Mo mot ket noi
                System.out.println("Dang ket noi toi co so du lieu ...");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);

                // Buoc 4: Thiet lap auto commit la false.
               // conn.setAutoCommit(false);

                // Buoc 5: Thuc thi truy van
                System.out.println("Tao cac lenh truy van SQL ...");
                stmt = conn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_UPDATABLE);

                // Buoc 6: Chen mot hang vao trong bang sinhvienk60
                System.out.println("\nChen mot hang ...");
                String SQL = "INSERT INTO hocvien " +
                        "VALUES (11, 'Tran Hung', 'Cuong', 7.5)";
                stmt.executeUpdate(SQL);

                // Buoc 7: Chen them mot hang vao trong bang sinhvienk60
                SQL = "INSERT INTO hocvien " +
                        "VALUES (7, 'Vu Ngoc', 'Phan', 6.5)";
                stmt.executeUpdate(SQL);

                // Buoc 8: Ky thac cac thay doi.
                System.out.println("\nKy thac cac thay doi ...");
               // conn.commit();

                // Buoc 9: Liet ke tat ca cac ban ghi.
                String sql = "SELECT * FROM hocvien";
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("\nLiet ke result set de tham chieu ...");
                printRs(rs);

                // Buoc 10: Don sach moi truong va giai phong resource
                rs.close();
                stmt.close();
                conn.close();
            }catch(SQLException se){
                // Xu ly cac loi cho JDBC
                se.printStackTrace();
                // Neu xuat hien loi thi xoa sach cac thay doi
                // va tro ve trang thai truoc khi co thay doi.
                System.out.println("\nQuay ve trang thai truoc thay doi ...");
                try{
                    if(conn!=null)
                        conn.rollback();
                }catch(SQLException se2){
                    se2.printStackTrace();
                }// Ket thuc khoi try

            }catch(Exception e){
                // Xu ly cac loi cho Class.forName
                e.printStackTrace();
            }finally{
                // Khoi finally duoc su dung de dong cac resource
                try{
                    if(stmt!=null)
                        stmt.close();
                }catch(SQLException se2){
                }
                try{
                    if(conn!=null)
                        conn.close();
                }catch(SQLException se){
                    se.printStackTrace();
                }// Ket thuc khoi finally
            }// Ket thuc khoi try

        }// Ket thuc main

        public static void printRs(ResultSet rs) throws SQLException{
            // Bao dam chung ta da bat dau tu hang dau tien
            rs.beforeFirst();
            //rs.afterLast();
            while(rs.next()){
                // Lay du lieu boi su dung ten cot
                int mssv  = rs.getInt(1);
                int diemthi = rs.getInt(4);
                String ho = rs.getString(2);
                String ten = rs.getString(3);

                // Hien thi cac gia tri
                System.out.print("\nMSSV: " + mssv);
                System.out.print("\nHo: " + ho);
                System.out.println("\nTen: " + ten);
                System.out.print("\nDiem Thi: " + diemthi);
                System.out.print("\n=================");
            }
            System.out.println();
        }// Ket thuc printRs()
    }// Ket thuc ViDuJDBC

