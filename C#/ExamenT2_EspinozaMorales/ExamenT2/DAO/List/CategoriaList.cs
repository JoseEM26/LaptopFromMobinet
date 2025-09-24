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
    public class CategoriaList
    {

        public List<Categoria> SeleccionarTodo()
        {
            SqlConnection con = null;
            SqlCommand cmd = null;
            SqlDataReader reader = null;
            List<Categoria> lista = new List<Categoria>();
            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                con.Open();

                cmd = new SqlCommand("usp_listarCategoria", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;


                reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Categoria Item = new Categoria()
                    {
                        CategoriaID = Convert.ToInt32(reader[0]),
                        Nombre = reader[1].ToString()
                    };

                    lista.Add(Item);
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