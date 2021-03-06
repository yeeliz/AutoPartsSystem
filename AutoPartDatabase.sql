USE [PartsSystem]
GO
/****** Object:  Table [dbo].[Automovil]    Script Date: 14/04/2015 04:47:33 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Automovil](
	[Modelo] [varchar](50) NOT NULL,
	[Detalle] [varchar](50) NOT NULL,
	[AnioDeFabricacion] [int] NOT NULL,
	[Fabricante] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Automovil] PRIMARY KEY CLUSTERED 
(
	[Modelo] ASC,
	[AnioDeFabricacion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Cliente]    Script Date: 14/04/2015 04:47:33 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Cliente](
	[Nombre] [varchar](50) NOT NULL,
	[Estado] [varchar](50) NOT NULL,
	[EsPersona] [bit] NOT NULL,
 CONSTRAINT [PK_Cliente] PRIMARY KEY CLUSTERED 
(
	[Nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Encargado]    Script Date: 14/04/2015 04:47:33 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Encargado](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[Telefono] [int] NOT NULL,
	[Cargo] [varchar](50) NOT NULL,
	[Organizacion] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Engargado] PRIMARY KEY CLUSTERED 
(
	[ID] ASC,
	[Organizacion] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [IX_Encargado] UNIQUE NONCLUSTERED 
(
	[Telefono] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Fabricante]    Script Date: 14/04/2015 04:47:33 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Fabricante](
	[Nombre] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Fabricante] PRIMARY KEY CLUSTERED 
(
	[Nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Marca]    Script Date: 14/04/2015 04:47:33 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Marca](
	[Nombre] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Marca] PRIMARY KEY CLUSTERED 
(
	[Nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[MarcasDePartes]    Script Date: 14/04/2015 04:47:33 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[MarcasDePartes](
	[NombreParte] [varchar](50) NOT NULL,
	[NombreMarca] [varchar](50) NOT NULL,
 CONSTRAINT [PK_MarcasDePartes] PRIMARY KEY CLUSTERED 
(
	[NombreParte] ASC,
	[NombreMarca] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Orden]    Script Date: 14/04/2015 04:47:33 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Orden](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Fecha] [varchar](50) NOT NULL,
	[Impuesto] [int] NOT NULL,
	[MontoDeVenta] [int] NOT NULL,
	[TotalCobrado] [int] NOT NULL,
	[Cliente] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Orden] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [IX_Orden] UNIQUE NONCLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Organizaciones]    Script Date: 14/04/2015 04:47:33 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Organizaciones](
	[CedulaJuridica] [int] NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[Direccion] [varchar](50) NULL,
	[Ciudad] [varchar](50) NULL,
	[IDEncargado] [int] NOT NULL,
 CONSTRAINT [PK_Organizaciones_1] PRIMARY KEY CLUSTERED 
(
	[Nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [IX_Organizaciones] UNIQUE NONCLUSTERED 
(
	[CedulaJuridica] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [Unique_FK] UNIQUE NONCLUSTERED 
(
	[IDEncargado] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Parte]    Script Date: 14/04/2015 04:47:33 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Parte](
	[Nombre] [varchar](50) NOT NULL,
	[Fabricante] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Parte] PRIMARY KEY CLUSTERED 
(
	[Nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PartesDeAutomovil]    Script Date: 14/04/2015 04:47:33 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PartesDeAutomovil](
	[NombreParte] [varchar](50) NOT NULL,
	[AnioDeFabricacion] [int] NOT NULL,
	[ModeloAutomovil] [varchar](50) NOT NULL,
 CONSTRAINT [PK_PartesDeAutomovil] PRIMARY KEY CLUSTERED 
(
	[NombreParte] ASC,
	[AnioDeFabricacion] ASC,
	[ModeloAutomovil] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[PartesPorOrden]    Script Date: 14/04/2015 04:47:33 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PartesPorOrden](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[IdOrden] [int] NOT NULL,
	[IdPartesPorProveedor] [int] NOT NULL,
	[Precio] [int] NOT NULL,
	[Cantidad] [int] NOT NULL,
 CONSTRAINT [PK_PartesPorOrden_1] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[PartesPorProveedor]    Script Date: 14/04/2015 04:47:33 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[PartesPorProveedor](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[IDProveedor] [int] NOT NULL,
	[NombreParte] [varchar](50) NOT NULL,
	[PorcentajeDeGanancia] [int] NOT NULL,
	[Precio] [int] NOT NULL,
 CONSTRAINT [PK_PartesPorProveedor] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Persona]    Script Date: 14/04/2015 04:47:33 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Persona](
	[Nombre] [varchar](50) NOT NULL,
	[Cedula] [int] NOT NULL,
	[Direccion] [varchar](50) NOT NULL,
	[Ciudad] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Persona] PRIMARY KEY CLUSTERED 
(
	[Nombre] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY],
 CONSTRAINT [Unique_Persona] UNIQUE NONCLUSTERED 
(
	[Cedula] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Proveedor]    Script Date: 14/04/2015 04:47:33 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Proveedor](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[Direccion] [varchar](50) NOT NULL,
	[Ciudad] [varchar](50) NOT NULL,
	[Nombre] [varchar](50) NOT NULL,
	[NombreContacto] [varchar](50) NOT NULL,
 CONSTRAINT [PK_Proveedor] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[TelefonoProveedor]    Script Date: 14/04/2015 04:47:33 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[TelefonoProveedor](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[IDProveedor] [int] NOT NULL,
	[Numero] [varchar](50) NOT NULL,
 CONSTRAINT [PK_TelefonoProveedor] PRIMARY KEY CLUSTERED 
(
	[ID] ASC,
	[IDProveedor] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Telefonos]    Script Date: 14/04/2015 04:47:33 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Telefonos](
	[NombrePersona] [varchar](50) NOT NULL,
	[Numero] [int] NOT NULL,
 CONSTRAINT [PK_Telefonos_1] PRIMARY KEY CLUSTERED 
(
	[Numero] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
ALTER TABLE [dbo].[Automovil]  WITH CHECK ADD  CONSTRAINT [FK_Automovil_Fabricante] FOREIGN KEY([Fabricante])
REFERENCES [dbo].[Fabricante] ([Nombre])
GO
ALTER TABLE [dbo].[Automovil] CHECK CONSTRAINT [FK_Automovil_Fabricante]
GO
ALTER TABLE [dbo].[MarcasDePartes]  WITH CHECK ADD  CONSTRAINT [FK_MarcasDePartes_Marca1] FOREIGN KEY([NombreMarca])
REFERENCES [dbo].[Marca] ([Nombre])
GO
ALTER TABLE [dbo].[MarcasDePartes] CHECK CONSTRAINT [FK_MarcasDePartes_Marca1]
GO
ALTER TABLE [dbo].[MarcasDePartes]  WITH CHECK ADD  CONSTRAINT [FK_MarcasDePartes_Parte] FOREIGN KEY([NombreParte])
REFERENCES [dbo].[Parte] ([Nombre])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[MarcasDePartes] CHECK CONSTRAINT [FK_MarcasDePartes_Parte]
GO
ALTER TABLE [dbo].[Orden]  WITH CHECK ADD  CONSTRAINT [FK_Orden_Cliente] FOREIGN KEY([Cliente])
REFERENCES [dbo].[Cliente] ([Nombre])
GO
ALTER TABLE [dbo].[Orden] CHECK CONSTRAINT [FK_Orden_Cliente]
GO
ALTER TABLE [dbo].[Organizaciones]  WITH CHECK ADD  CONSTRAINT [FK_Organizaciones_Cliente] FOREIGN KEY([Nombre])
REFERENCES [dbo].[Cliente] ([Nombre])
GO
ALTER TABLE [dbo].[Organizaciones] CHECK CONSTRAINT [FK_Organizaciones_Cliente]
GO
ALTER TABLE [dbo].[Organizaciones]  WITH CHECK ADD  CONSTRAINT [FK_Organizaciones_Encargado] FOREIGN KEY([IDEncargado], [Nombre])
REFERENCES [dbo].[Encargado] ([ID], [Organizacion])
GO
ALTER TABLE [dbo].[Organizaciones] CHECK CONSTRAINT [FK_Organizaciones_Encargado]
GO
ALTER TABLE [dbo].[Parte]  WITH CHECK ADD  CONSTRAINT [FK_Parte_Fabricante] FOREIGN KEY([Fabricante])
REFERENCES [dbo].[Fabricante] ([Nombre])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Parte] CHECK CONSTRAINT [FK_Parte_Fabricante]
GO
ALTER TABLE [dbo].[PartesDeAutomovil]  WITH CHECK ADD  CONSTRAINT [FK_PartesDeAutomovil_Automovil] FOREIGN KEY([ModeloAutomovil], [AnioDeFabricacion])
REFERENCES [dbo].[Automovil] ([Modelo], [AnioDeFabricacion])
GO
ALTER TABLE [dbo].[PartesDeAutomovil] CHECK CONSTRAINT [FK_PartesDeAutomovil_Automovil]
GO
ALTER TABLE [dbo].[PartesDeAutomovil]  WITH CHECK ADD  CONSTRAINT [FK_PartesDeAutomovil_Parte] FOREIGN KEY([NombreParte])
REFERENCES [dbo].[Parte] ([Nombre])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PartesDeAutomovil] CHECK CONSTRAINT [FK_PartesDeAutomovil_Parte]
GO
ALTER TABLE [dbo].[PartesPorOrden]  WITH CHECK ADD  CONSTRAINT [FK_PartesPorOrden_Orden1] FOREIGN KEY([IdOrden])
REFERENCES [dbo].[Orden] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PartesPorOrden] CHECK CONSTRAINT [FK_PartesPorOrden_Orden1]
GO
ALTER TABLE [dbo].[PartesPorOrden]  WITH CHECK ADD  CONSTRAINT [FK_PartesPorOrden_PartesPorProveedor] FOREIGN KEY([IdPartesPorProveedor])
REFERENCES [dbo].[PartesPorProveedor] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PartesPorOrden] CHECK CONSTRAINT [FK_PartesPorOrden_PartesPorProveedor]
GO
ALTER TABLE [dbo].[PartesPorProveedor]  WITH CHECK ADD  CONSTRAINT [FK_PartesPorProveedor_Parte] FOREIGN KEY([NombreParte])
REFERENCES [dbo].[Parte] ([Nombre])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PartesPorProveedor] CHECK CONSTRAINT [FK_PartesPorProveedor_Parte]
GO
ALTER TABLE [dbo].[PartesPorProveedor]  WITH CHECK ADD  CONSTRAINT [FK_PartesPorProveedor_Proveedor] FOREIGN KEY([IDProveedor])
REFERENCES [dbo].[Proveedor] ([ID])
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[PartesPorProveedor] CHECK CONSTRAINT [FK_PartesPorProveedor_Proveedor]
GO
ALTER TABLE [dbo].[Persona]  WITH CHECK ADD  CONSTRAINT [FK_Persona_Cliente] FOREIGN KEY([Nombre])
REFERENCES [dbo].[Cliente] ([Nombre])
GO
ALTER TABLE [dbo].[Persona] CHECK CONSTRAINT [FK_Persona_Cliente]
GO
ALTER TABLE [dbo].[TelefonoProveedor]  WITH CHECK ADD  CONSTRAINT [FK_TelefonoProveedor_Proveedor1] FOREIGN KEY([IDProveedor])
REFERENCES [dbo].[Proveedor] ([ID])
GO
ALTER TABLE [dbo].[TelefonoProveedor] CHECK CONSTRAINT [FK_TelefonoProveedor_Proveedor1]
GO
ALTER TABLE [dbo].[Telefonos]  WITH CHECK ADD  CONSTRAINT [FK_Telefonos_Persona] FOREIGN KEY([NombrePersona])
REFERENCES [dbo].[Persona] ([Nombre])
GO
ALTER TABLE [dbo].[Telefonos] CHECK CONSTRAINT [FK_Telefonos_Persona]
GO
/****** Object:  StoredProcedure [dbo].[AssociateBrandAndPart]    Script Date: 14/04/2015 04:47:33 a.m. ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		<Joshua>
-- Create date: <2,4,15>
-- Description:	<asocia marca con parte>
-- =============================================
CREATE PROCEDURE [dbo].[AssociateBrandAndPart]
	-- Add the parameters for the stored procedure here
	@PartName VARCHAR(50),
	@BrandName VARCHAR(50)
AS
BEGIN
	INSERT INTO [MarcasDePartes] (NombreParte,NombreMarca) 
		SELECT p.Nombre,m.Nombre
		FROM Parte p,Marca m
			WHERE	p.Nombre = @PartName AND m.Nombre = @BrandName
END






GO
EXEC sys.sp_addextendedproperty @name=N'Descripcion:', @value=N'Detalles del automovil como su modelo y anio de fabricacion, los cuales juntos son su llave, ademas tiene una descripcion del vehiculo y el una relacion con el fabricante que lo hizo' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Automovil'
GO
EXEC sys.sp_addextendedproperty @name=N'Desc:', @value=N'El nombre de la cliente su estado y un bit para indicar si es una persona o un negocio. Si el bit esta prendido es persona' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Cliente'
GO
EXEC sys.sp_addextendedproperty @name=N'Descripcion:', @value=N'Detalles de la persona de contacto del cliente tipo empresarial. Es una relacion 1:1 con la organizacion, tiene datos como el nombre del Encargado su telefono, su cargo ademas del id de la organizacion como FK + su propio ID' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Encargado'
GO
EXEC sys.sp_addextendedproperty @name=N'Descrip:', @value=N'Nombre del fabricante que es el PK y unico atributo' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Fabricante'
GO
EXEC sys.sp_addextendedproperty @name=N'Descripcion:', @value=N'Unico atributo Nombre de la marca que tambien es el PK' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Marca'
GO
EXEC sys.sp_addextendedproperty @name=N'Descripcion', @value=N'Relaciona la relacion M:M de Parte con Marca. Las llaves de las dos juntas son su llave' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'MarcasDePartes'
GO
EXEC sys.sp_addextendedproperty @name=N'Descrip:', @value=N'Tiene los detalles principales de la orden: Fecha orden, el monto de impuesto de venta, el total cobrado por la orden y el nombre del cliente quien realizo la orden' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Orden'
GO
EXEC sys.sp_addextendedproperty @name=N'Descripcion:', @value=N'Informacion sobre el cliente de tipo Empresarial. Con tiene el nombre de la rempre, su cedulaJuridica, la direccion y cuidad donde esta ubicado y el ID de la persona de contacto dentro de la empresa' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Organizaciones'
GO
EXEC sys.sp_addextendedproperty @name=N'Descrip:', @value=N'El nombre (PK) y Nombre del fabricante de dicho parte' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Parte'
GO
EXEC sys.sp_addextendedproperty @name=N'Descripcion:', @value=N'Relaciona la relacion M:M de Automovil con Parte, tiene el nombre de la parte, y el modelo+annoDeFrabricaion del vehicul' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PartesDeAutomovil'
GO
EXEC sys.sp_addextendedproperty @name=N'Descrip:', @value=N'Relaciona la relacion M:M de "PartesPorProveedor" con "Orden", almacena el Id de la orden y el id del proveedor de la partes; admeas del precio y cantidad de dicho parte.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PartesPorOrden'
GO
EXEC sys.sp_addextendedproperty @name=N'Descrip:', @value=N'Relaciona la entidad Proveedor con Parte y a la vez con la entidad "PartesPorOrden" aqui se almacena el id del provedor, el nombre del parte deseado, el precio de dicho y el porcentaje de ganancia del provedor.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'PartesPorProveedor'
GO
EXEC sys.sp_addextendedproperty @name=N'Descrip:', @value=N'Detalles adicionales del cliente tipo Persona. Contiene el numero de CED de dicho persona con su direccion y cuidad de residencia' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Persona'
GO
EXEC sys.sp_addextendedproperty @name=N'Des:', @value=N'Esta tabla contiene todo los telefonos de dicho provedor. Se relaciona con la tabla de proveedor por medio de una FK al id del proveed, la llave de esta talba es el FK + su PK.' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'TelefonoProveedor'
GO
EXEC sys.sp_addextendedproperty @name=N'Desc:', @value=N'Contiene todo los telefonos de los clientes tipo persona. Los numeros son identificados por el mismo num de telefono y estan relacionados con su "dueno" por medio del nombre de esa persona' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'Telefonos'
GO
