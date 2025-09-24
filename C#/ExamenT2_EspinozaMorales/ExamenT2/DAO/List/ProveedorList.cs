using ExamenT2.Models;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data.SqlClient;
using System.Diagnostics;
using System.Linq;
using System.Web;

namespace ExamenT2.DAO.List
{
    public class ProveedorList
    {
        
            public List<Proveedor> SeleccionarTodo()
            {
                SqlConnection con = null;
                SqlCommand cmd = null;
                SqlDataReader reader = null;
                List<Proveedor> lista = new List<Proveedor>();
                try
                {
                    con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                    con.Open();

                    cmd = new SqlCommand("usp_listar_proovedor", con);
                    cmd.CommandType = System.Data.CommandType.StoredProcedure;


                    reader = cmd.ExecuteReader();

                    while (reader.Read())
                    {
                        Proveedor s = new Proveedor()
                        {
                            idProveedor = Convert.ToInt32(reader[0]),
                            nombreProveedor = reader[1].ToString()
                        };

                        lista.Add(s);
                    }




                }
                catch (Exception e)
                {
                    Debug.WriteLine(e.Message);
                }
                finally
                {
                    con.Close();
                    reader.Close();
                }
                return lista;
            }

        

    }
}