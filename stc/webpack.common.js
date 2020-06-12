const fs = require('fs');
const path = require('path');

const CopyWebpackPlugin = require('copy-webpack-plugin');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CleanWebpackPlugin = require('clean-webpack-plugin');
// const MiniCssExtractPlugin = require('mini-css-extract-plugin');

const { views } = require('./webpack.config');

const dist = path.resolve(__dirname, 'dist');

console.log(views);

const js = fs.readdirSync(path.join(__dirname, './public/js'));

const entry = {};
js.forEach((filename) => {
  const name = filename.split('.')[0];
  entry[name] = `./public/js/${filename}`;
});

console.log(entry);

module.exports = {
  entry: {
    ...entry
  },
  output: {
    publicPath: '', // prefix path should be '/', for github page please use empty path
    path: dist,
    filename: 'public/js/[name].js'
  },

  module: {
    rules: [
      // {
      //   test: /\.(sa|sc|c)ss$/,
      //   use: [
      //     MiniCssExtractPlugin.loader, // split css to files, not use style-loader
      //     'css-loader',
      //     /**
      //      * postcss-loader
      //      * Use it after css-loader and style-loader,
      //      * but before other preprocessor loaders
      //      * like e.g sass|less|stylus-loader.
      //      *
      //      * Config file: postcss.config.js
      //      */
      //     'postcss-loader',
      //     'sass-loader'
      //   ]
      // }
      // {
      //   test: /\.(html|xtm)$/,
      //   use: {
      //     loader: 'html-loader',
      //     options: {
      //       attrs: ['img:data-src'],
      //       minimize: false,
      //       collapseWhitespace: true,
      //       removeComments: true,
      //       interpolate: true
      //     }
      //   }
      // }
    ]
  },

  plugins: [
    // new MiniCssExtractPlugin({
    //   publicPath: '', // prefix path should be '/', for github page please use empty path
    //   path: dist,
    //   filename: 'css/[name].[hash].css', // in directory ./dist/css/
    //   chunkFilename: 'css/[name].[hash].css' // in directory ./dist/css/
    // }),

    new CleanWebpackPlugin([dist]),

    new CopyWebpackPlugin([{
      from: path.resolve(__dirname, './public'),
      to: path.resolve(dist, 'public')
    }]),

    ...views.map(({ template, filename, js }) => new HtmlWebpackPlugin({
      template,
      filename: `view/${filename}`,
      // chunks: ['runtime', 'vendors', ...js],
      chunks: [],
      minify: {
        caseSensitive: true,
        collapseWhitespace: true,
        removeComments: true,
        minifyCSS: true,
        minifyJS: true,
        minifyURLs: false
      }
    }))
  ],

  optimization: {
    runtimeChunk: 'single',
    splitChunks: {
      chunks: 'all',
      cacheGroups: {
        vendor: {
          test: /[\\/]node_modules[\\/]/,
          name: 'vendors',
          chunks: 'all'
        }
      }
    }
  }
};
