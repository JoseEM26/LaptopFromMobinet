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
    public class EstadoList
    {
        public List<Estado> SeleccionarTodo()
        {
            SqlConnection con = null;
            SqlCommand cmd = null;
            SqlDataReader reader = null;
            List<Estado> lista = new List<Estado>();
            try
            {
                con = new SqlConnection(ConfigurationManager.ConnectionStrings["db"].ConnectionString);
                con.Open();

                cmd = new SqlCommand("usp_listar_estado", con);
                cmd.CommandType = System.Data.CommandType.StoredProcedure;


                reader = cmd.ExecuteReader();

                while (reader.Read())
                {
                    Estado Item = new Estado()
                    {
                        idEstado = Convert.ToInt32(reader[0]),
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